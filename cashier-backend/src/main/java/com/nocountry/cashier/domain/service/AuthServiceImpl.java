package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.AuthRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.AuthResponseDTO;
import com.nocountry.cashier.domain.usecase.AuthService;
import com.nocountry.cashier.exception.DuplicateEntityException;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.JwtGenericException;
import com.nocountry.cashier.persistance.entity.TokenEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.UserMapper;
import com.nocountry.cashier.persistance.repository.TokenRepository;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    @Modifying
    public AuthResponseDTO register(UserRequestDTO userRequestDTO) {
        String message = "El usuario con dni " + userRequestDTO.getDni() + " ya existe.";

        Optional<UserEntity> user = userRepository.findByEmailIgnoreCase(userRequestDTO.getEmail().strip());
        Optional<UserEntity> dni = userRepository.findByDni(userRequestDTO.getDni().strip());
        if (user.isPresent()) throw new DuplicateEntityException("El usario ya existe. Ingrese otro correo");
        if (dni.isPresent()) throw new DuplicateEntityException(message);

        UserEntity auth = mapper.toUserEntity(userRequestDTO);
//        String token = jwtTokenProvider.generateToken(auth);
//        auth.setToken(TokenEntity.builder().tokenGenerated(token).build());
        auth.setEnabled(Boolean.TRUE);

        userRepository.save(auth);
        return AuthResponseDTO.builder()
                .message("Se registró correctamente")
//                .token(token)
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

        return verify ? AuthResponseDTO.builder()
                .message("Authenticación correcta")
                .token(jwtTokenProvider.generateToken(user.get()))
                .build() : AuthResponseDTO.builder()
//                .token("N/A")
                .message("Password o email incorrectos").build();
    }
}
