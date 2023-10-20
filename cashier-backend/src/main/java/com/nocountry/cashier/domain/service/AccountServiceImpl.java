package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.response.AccountResponseDTO;
import com.nocountry.cashier.domain.usecase.AccountService;

import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.UserEntity;
import com.nocountry.cashier.persistance.mapper.AccountMapper;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import com.nocountry.cashier.persistance.repository.UserRepository;
import com.nocountry.cashier.util.GeneratorCVU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountResponseDTO> getAllAccounts() {

        return accountMapper.toGetAccountDTOList(accountRepository.findAll());
    }

    @Override
    public AccountResponseDTO getAccount(String idAccount) {

        AccountResponseDTO accountResponseDTO =  accountMapper.toGetAccountDTO(accountRepository.findById(idAccount)
                                                .orElse(null));

        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO createAccount(String uuidUser) {

        UserEntity userEntity = userRepository.findById(uuidUser).orElse(null);

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setTotalAccount(BigDecimal.ZERO);
        accountEntity.setOpenAccountDate(LocalDate.now());
        accountEntity.setCvu((GeneratorCVU.generate("452", 22)));
        accountEntity.setStatus(true);

        //lanzar excepcion
       /*if(userEntity.getAccountEntity().getIdAccount() == null) {
            //guardar
        }
        else{

        }*/
        userEntity.setAccountEntity(accountEntity);

        userRepository.save(userEntity);

        return accountMapper.toGetAccountDTO(accountEntity);
    }



    @Override
    public void deleteAccount(String uuidAccount) {

        accountRepository.deleteById(uuidAccount);
    }
}
