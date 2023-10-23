package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.controller.dto.response.CreditCardResponseDTO;
import com.nocountry.cashier.domain.usecase.CreditCardService;
import com.nocountry.cashier.exception.DuplicateEntityException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.CreditCardEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.CreditCardMapper;
import com.nocountry.cashier.persistance.repository.CreditCardRepository;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.util.GeneratorCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardMapper cardMapper;


    @Override
    public List<CreditCardResponseDTO> getAllCards() {
        return cardMapper.toGetListCardDTO(creditCardRepository.findAll());
    }

    @Override
    public CreditCardResponseDTO getCard(String idCard) {
        CreditCardResponseDTO creditCardResponseDTO =  cardMapper.togetCardDTO
                                (creditCardRepository.findById(idCard)
                                .orElse(null));

        return creditCardResponseDTO;
    }

    @Override
    public CreditCardResponseDTO createCard(String uuidUser) {

        UserEntity userEntity = userRepository.findById(uuidUser).orElse(null);

        System.out.println("usario" +userEntity.getCreditCardEntity() );
        if(userEntity.getCreditCardEntity() != null) {
            throw new DuplicateEntityException("Error!! El usuario ya posee una Tarjeta!!!");
        }

        CreditCardEntity creditCardEntity = new CreditCardEntity();

        creditCardEntity.setCardName(userEntity.getName() + " " + userEntity.getLastName());
        creditCardEntity.setCardNumber(GeneratorCardNumber.generate("518", 16));
        creditCardEntity.setExpirationDate(LocalDate.now().plusYears(5));
        creditCardEntity.setSecurityCode(GeneratorCardNumber.getCode());
        creditCardEntity.setEnabled(true);

        //no es necesario va por detras con cascade
        //creditCardRepository.save(creditCardEntity);

        userEntity.setCreditCardEntity(creditCardEntity);

        userRepository.save(userEntity);

        return cardMapper.togetCardDTO(creditCardEntity);
    }

    @Override
    public void deleteCard(String idCard) {

        creditCardRepository.deleteById(idCard);
    }
}
