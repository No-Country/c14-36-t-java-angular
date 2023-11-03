package com.nocountry.cashier.domain.service;

import com.google.zxing.WriterException;
import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthConfirmedDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.controller.dto.response.AuthenticatedUserDTO;
import com.nocountry.cashier.domain.service.email.EmailFactoryService;
import com.nocountry.cashier.domain.usecase.AccountService;
import com.nocountry.cashier.domain.usecase.AuthService;
import com.nocountry.cashier.domain.usecase.CreditCardService;
import com.nocountry.cashier.domain.usecase.qr.QRGeneratorService;
import com.nocountry.cashier.enums.EnumsEmail;
import com.nocountry.cashier.enums.EnumsTemplate;
import com.nocountry.cashier.exception.DuplicateEntityException;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.JwtGenericException;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.TokenEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.AccountMapper;
import com.nocountry.cashier.persistance.mapper.CreditCardMapper;
import com.nocountry.cashier.persistance.mapper.UserMapper;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.security.jwt.JwtTokenProvider;
import com.nocountry.cashier.util.Utility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static com.nocountry.cashier.util.Constant.HEIGHT_QR;
import static com.nocountry.cashier.util.Constant.WIDTH_QR;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailFactoryService emailService;
    private final QRGeneratorService qrGeneratorService;
    private final AccountService accountService;
    private final CreditCardService creditCardService;
    private final Utility utility;

    @Value("${jwt.time.expiration}")
    private String expirationToken;
    @Value("${jwt.time.expiration.confirm.email}")
    private String expirationEmail;

    @Override
    @Transactional
    @Modifying
    public AuthResponseDTO register(UserRequestDTO userRequestDTO, String url) {
        final String message = "El usuario con dni " + userRequestDTO.getDni() + " ya existe.";
        final String subject = "CONFIRM YOUR EMAIL";

        Optional<UserEntity> user = userRepository.findByEmailIgnoreCase(userRequestDTO.getEmail().strip());
        Optional<UserEntity> byDni = userRepository.findByDni(userRequestDTO.getDni().strip());
        if (user.isPresent()) throw new DuplicateEntityException("El usario ya existe. Ingrese otro correo");
        if (byDni.isPresent()) throw new DuplicateEntityException(message);

        UserEntity auth = mapper.toUserEntity(userRequestDTO);

        UserEntity userTokenInfo = new UserEntity();
        userTokenInfo.setName(auth.getName());
        userTokenInfo.setEmail(auth.getEmail());
        userTokenInfo.setLastName(auth.getLastName());

        String token = jwtTokenProvider.generateToken(userTokenInfo, expirationEmail);
        auth.setToken(TokenEntity.builder().tokenGenerated(token).build());

        userRepository.save(auth);

        emailService.generateEmail(EnumsTemplate.CONFIRM_EMAIL,
                EnumsEmail.GMAIL,
                new String[]{auth.getEmail()},
                subject,
                jwtTokenProvider.getClaimForToken(token, "name"), url + token);

        return AuthResponseDTO.builder()
                .message("A confirmation email was sent to your registered email address.")
                .build();
    }

    @Override
    @Transactional
    public AuthenticatedUserDTO authenticate(AuthRequestDTO authRequestDTO) {
        Optional<UserEntity> user = userRepository.findByEmailIgnoreCase(authRequestDTO.email().strip());
        if (user.isEmpty()) throw new GenericException("Error al autenticarse. Usuario no existe", HttpStatus.NOT_FOUND);
        if (Boolean.FALSE.equals(user.get().getVerify())){
            throw new GenericException("El usuario no ha confirmado su email.", HttpStatus.CONFLICT);
        }
        boolean verify = passwordEncoder.matches(authRequestDTO.password(), user.get().getPassword());
        return verify
                ? AuthenticatedUserDTO.builder()
                .id(user.get().getId())
                .success(true)
                .message("Autenticación correcta")
                .token(jwtTokenProvider.generateToken(user.get(), expirationToken))
                .build()
                : AuthenticatedUserDTO.builder()
                .success(false)
                .message("Password o email incorrectos")
                .build();
    }

    @Override
    @Transactional
    @Modifying
    public AuthConfirmedDTO confirm(String token) {
        final ResponseEntity<Resource> qrCodeImage;
        // * AQUI OBTENEMOS EL TOKEN CON UN TIEMPO DE EXPIRACION BAJA SI PASA EL TIEMPO ES POR QUE EL USUARIO NO HA CONFIRMADO EL EMAIL
        if (!jwtTokenProvider.verifyToken(token)) {
            log.error("No se ha confirmado el email");
            throw new JwtGenericException("Oops, expiró el tiempo para confirmar su email", HttpStatus.CONFLICT);
        }
        var emailUser = jwtTokenProvider.getClaimForToken(token, "sub");
        var user = userRepository.findByEmailIgnoreCase(emailUser).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        //user.setEnabled(Boolean.TRUE);
        user.setVerify(Boolean.TRUE);

        var filename = user.getDni() + "_" + user.getLastName() + "_" + "QRCODE.png"; //nombre del archivo original con su extension

        accountService.createAccount(user.getId());
        creditCardService.createCard(user.getId());
        try {
            String qrContent = "\tNombre: " + user.getName() + " " + user.getLastName() + "\n" +
                    "\tCuenta: " + user.getAccountEntity().getCvu() + "\n" +
                    "\tNumero tarjeta: " + user.getCreditCardEntity().getCardNumber();
            qrCodeImage = qrGeneratorService.generateQrCodeImage(qrContent, WIDTH_QR, HEIGHT_QR, filename);
            user.setQr(Objects.requireNonNull(qrCodeImage.getBody()).getFilename());
        } catch (WriterException e) {
            log.info("Hubo un problema al generar el Qr");
            throw new GenericException("Failed to write QR code image to output stream.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // ? GENERAMOS EL USUARIO , GENERAMOS NUEVO TOKEN CON NUEVO TIEMPO DE EXPIRACION
        //String newToken = jwtTokenProvider.generateToken(user, expirationToken);
        log.info("SE CONFIRMÓ SU EMAIL, CUENTA ACTIVADA");
        var nameUser = jwtTokenProvider.getClaimForToken(token, "name");
        return AuthConfirmedDTO.builder()
                .message("Perfil creado correctamente para el usuario " + nameUser)
                .id(user.getId())
                .qr(Objects.requireNonNull(qrCodeImage.getBody()).getFilename())
                .build();
    }

    private boolean authenticateWithOtp(){
        String otp = utility.generatorOTP(6);
        return false;
    }


}
