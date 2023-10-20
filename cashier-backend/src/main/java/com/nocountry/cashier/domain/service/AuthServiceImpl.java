package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.domain.service.email.EmailFactoryService;
import com.nocountry.cashier.domain.usecase.AuthService;
import com.nocountry.cashier.enums.EnumsEmail;
import com.nocountry.cashier.enums.EnumsTemplate;
import com.nocountry.cashier.exception.DuplicateEntityException;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.JwtGenericException;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.TokenEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.UserMapper;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.security.jwt.JwtTokenProvider;
import com.nocountry.cashier.util.Constant;
import com.nocountry.cashier.util.Utility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

import static com.nocountry.cashier.util.Constant.*;

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

        auth.setEnabled(Boolean.TRUE);
        userRepository.save(auth);

        emailService.generateEmail(EnumsTemplate.CONFIRM_EMAIL,
                EnumsEmail.GMAIL,
                new String[]{auth.getEmail()},
                subject,
                jwtTokenProvider.getClaimForToken(token,"name"), url+token);

        return AuthResponseDTO.builde()
                .message("A confirmation email was sent to your registered email address.")
                .token("Confirm your Email")
                .build();
    }

    @Override
    @Transactional
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {

        Optional<UserEntity> user = userRepository.findByEmailIgnoreCase(authRequestDTO.email().strip());
        if (user.isEmpty()) throw new GenericException("El usuario no existe.", HttpStatus.NOT_FOUND);
        boolean verify = passwordEncoder.matches(authRequestDTO.password(), user.get().getPassword());

        // ? ME TRAE EL TOKEN DE BASE DE DATOS
        //TokenEntity tokenBD = tokenRepository.findByTokenGenerated(user.get().getToken().getTokenGenerated());

        //? TOKEN QUE VIENE DEL HEADER

//        if (Objects.isNull(token) || !token.startsWith("Bearer ")) throw new JwtGenericException("Token Not found",HttpStatus.BAD_REQUEST);
//        String jwt=token.substring(7);

        //* comprueba que no venga vacío de la base de datos
        //if (Objects.isNull(tokenBD)) throw new JwtGenericException("Oops no puede ingresar", HttpStatus.BAD_REQUEST);

//        if (!jwtTokenProvider.verifyToken(jwt)) //tokenBD.getTokenGenerated()
//            throw new JwtGenericException("Oops Token Invalido", HttpStatus.BAD_REQUEST);


        return verify
                ? AuthResponseDTO.builder()
                .message("Autenticación correcta")
                .token(jwtTokenProvider.generateToken(user.get()))
                .build() 
                :AuthResponseDTO.builder()
                .message("Password o email incorrectos").build();
    }

    @Override
    @Transactional
    public Map<String, Object> confirm(String token) {

        if (jwtTokenProvider.verifyToken(token)) log.error("El token ha sido modificado. No valido");
        var emailUser = jwtTokenProvider.getClaimForToken(token, "sub");
        var user=userRepository.findByEmailIgnoreCase(emailUser).orElseThrow(()-> new ResourceNotFoundException("El usuario no existe"));
        user.setEnabled(Boolean.TRUE);
        userRepository.save(user);
        log.info("SE CONFIRMÓ SU EMAIL, CUENTA ACTIVADA");
        var nameUser =jwtTokenProvider.getClaimForToken(token,"name");
        return Map.of("message","Perfil creado para el usuario "+nameUser);
    }


}
