package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.request.UpdateRequestDTO;
import com.nocountry.cashier.controller.dto.request.UserRequestDTO;
import com.nocountry.cashier.controller.dto.response.ImageResponseDTO;
import com.nocountry.cashier.controller.dto.response.TransactionResponseDTO;
import com.nocountry.cashier.controller.dto.response.UserResponseDTO;
import com.nocountry.cashier.domain.usecase.UserService;
import com.nocountry.cashier.domain.usecase.firebase.FirebaseService;
import com.nocountry.cashier.exception.DuplicateEntityException;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.InvalidEmailException;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.ImageEntity;
import com.nocountry.cashier.persistance.entity.TransactionEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.ImageMapper;
import com.nocountry.cashier.persistance.mapper.UserMapper;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final Utility utility;
    private final FirebaseService firebaseService;
    private final ImageMapper imageMapper;


    @Transactional
    @Modifying
    @Override
    public UserResponseDTO create(UserRequestDTO data) {
        UserEntity userSave;
        UserEntity filtro2 = userRepository.findByEmailIgnoreCase(data.getEmail().strip()).orElse(null);
        UserEntity filtro = userRepository.findByDni(data.getDni().strip()).orElse(null);
        if (filtro2!=null|| filtro != null) throw new DuplicateEntityException("Oops el cliente ya existe");

        userSave = Optional.of(data)
                .map(mapper::toUserEntity)
                .map(userRepository::save)
                .orElseThrow(() -> new GenericException("Oops ocurrió un error. dni o email ya existe!!", HttpStatus.BAD_REQUEST));

        return mapper.toUserResponseDto(userSave);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getAll(PageableDto pageableDto) {
        Pageable pageable = utility.setPageable(pageableDto);
        Page<UserEntity> products = userRepository.findAll(pageable);
        Predicate<UserEntity> predicate = UserEntity::getEnabled;
        List<UserResponseDTO> responseDtoList = products.getContent().stream()
                .filter(predicate)
                .map(mapper::toUserResponseDto)
                .toList();
        return new PageImpl<>(responseDtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> getById(String uuid) {
        Function<String, Optional<UserEntity>> function = userRepository::findById;
        UserEntity userEntity = function.apply(uuid).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado", "uuid", uuid));
        return Optional.of(mapper.toUserResponseDto(userEntity));
    }

    @Override
    public UserResponseDTO update(String uuid, UserRequestDTO data) {
        return null;
    }

    @Override
    public boolean delete(String uuid) {
        if (!StringUtils.hasText(uuid))
            throw new GenericException("El campo no puede estar vacío", HttpStatus.BAD_REQUEST);
        UserEntity userEntity = userRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra con id: ", uuid));
        userRepository.deleteById(userEntity.getId());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getClienteByEmail(String email) {
        Predicate<String> verifyEmail = Utility::validateEmail;
        if (!verifyEmail.test(email)) throw new InvalidEmailException("El correo ingresado debe ser tipo gmail");
        UserEntity userEntity = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("Oops el Usuario no existe o ha sido borrado"));
        if (!userEntity.getEnabled()) throw new ResourceNotFoundException("Oops el Usuario se encuentra desactivado.");
        return mapper.toUserResponseDto(userEntity);
    }

    @Override
    public UserResponseDTO getClienteByDni(String dni) {
        return null;
    }

    @Override
    @Transactional
    @Modifying
    public UserResponseDTO customisedUpdate(UpdateRequestDTO data, String uuid) {
        Function<UpdateRequestDTO, Optional<UserEntity>> userId = userRequestDTO -> userRepository.findById(uuid);
        Optional<UserEntity> userEntity = userId.apply(data);
        if (userEntity.isEmpty())
            throw new ResourceNotFoundException(String.format("El cliente a modificar con id %s, no se encuentra", uuid));

        UserEntity modifyUser = userEntity.get().modifyUser(data);
        UserEntity saveUser = userRepository.save(modifyUser);
        return mapper.toUserResponseDto(saveUser);

    }

    @Override
    @Transactional
    public UserResponseDTO addUserWithImage(String uuid, MultipartFile file) {
        UserEntity userSave;
        UserEntity userEntity = userRepository.findById(uuid.strip()).orElse(null);
        if (userEntity == null)
            throw new DuplicateEntityException("Oops el Usuario no existe, no se le puede asignar una imagen de perfil.");

        ImageResponseDTO imageResponseDto = firebaseService.uploadImages(file);
        ImageEntity image = imageMapper.toImageEntity(imageResponseDto);
        userEntity.setImage(image);

        userSave = userRepository.save(userEntity);
        return mapper.toUserResponseDto(userSave);
    }

    @Override
    public Page<UserResponseDTO> findByShortString(String ramdom, PageableDto pageableDto) throws Exception {
        try{
            Pageable pageable = utility.setPageable(pageableDto);
            Page<UserEntity> UserPage = userRepository.findByShortString(ramdom,pageableDto);

            // Mapear la lista de entidades a DTOs
            List<UserResponseDTO> responseDtoList = UserPage.getContent().stream()
                    .map(mapper::toUserResponseDto)
                    .collect(Collectors.toList());

            return new PageImpl<>(responseDtoList, pageable, UserPage.getTotalElements());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
