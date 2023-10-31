package com.nocountry.cashier.domain.service;

import com.nocountry.cashier.controller.dto.request.BillRequestDTO;
import com.nocountry.cashier.controller.dto.request.PageableDto;
import com.nocountry.cashier.controller.dto.response.BillResponseDTO;
import com.nocountry.cashier.domain.strategy.transaction.Bill;
import com.nocountry.cashier.domain.strategy.transaction.TransactionStrategy;
import com.nocountry.cashier.domain.usecase.BillService;
import com.nocountry.cashier.enums.EnumsState;
import com.nocountry.cashier.enums.EnumsTransactions;
import com.nocountry.cashier.exception.ResourceNotFoundException;
import com.nocountry.cashier.persistance.entity.AccountEntity;
import com.nocountry.cashier.persistance.entity.BillEntity;
import com.nocountry.cashier.persistance.mapper.BillMapper;
import com.nocountry.cashier.persistance.repository.AccountRepository;
import com.nocountry.cashier.persistance.repository.BillRepository;
import com.nocountry.cashier.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final TransactionStrategy strategy;
    private final BillMapper mapper;
    private final Utility utility;
    private final AccountRepository accountRepository;

    @Transactional
    @Modifying
    @Override
    public BillResponseDTO createBill(BillRequestDTO data) {
        AccountEntity originAccount = accountRepository.findById(data.getOrigin()).orElseThrow(() ->
                new ResourceNotFoundException("Cuenta origen no encontrada", "id", data.getOrigin()));

        Bill strategyBill = (Bill) strategy.getTransaction(EnumsTransactions.valueOf(data.getType()));
        var bill = strategyBill.updateBalance(originAccount,data);
        bill.setState(EnumsState.DONE);
        bill.setType(EnumsTransactions.valueOf(data.getType()));

        BillEntity saveTransaction= billRepository.save(bill);
        return mapper.toBillResponseDto(saveTransaction);
    }

    @Override
    public BillResponseDTO findOneByIdAccount(String id, String idAccount) throws Exception {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillResponseDTO> findAllByIdAccount(String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<BillEntity> BillPage = billRepository.findAllByIdAccount(idAccount, pageable);

            // Mapear la lista de entidades a DTOs
            List<BillResponseDTO> responseDtoList = BillPage.getContent().stream()
                    .map(mapper::toBillResponseDto)
                    .toList();

            return new PageImpl<>(responseDtoList, pageable, BillPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<BillResponseDTO> findByState(EnumsState state, String idAccount, PageableDto pageableDto) throws Exception {
    return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillResponseDTO> findByType(String bill_type, String idAccount, PageableDto pageableDto) throws Exception {
        try {
            Pageable pageable = utility.setPageable(pageableDto);
            Page<BillEntity> BillPage = billRepository.findByType(bill_type.toLowerCase(), idAccount, pageable);
            // Mapear la lista de entidades a DTOs
            List<BillResponseDTO> responseDtoList = BillPage.getContent().stream()
                    .map(mapper::toBillResponseDto)
                    .toList();
            return new PageImpl<>(responseDtoList, pageable, BillPage.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<BillResponseDTO> findByAmount(BigDecimal amount, String idAccount, PageableDto pageableDto) throws Exception {
        return null;
    }
    @Override
    public BillResponseDTO create(BillRequestDTO data) {
        return null;
    }

    @Override
    public Page<BillResponseDTO> getAll(PageableDto pageable) {
        return null;
    }

    @Override
    public Optional<BillResponseDTO> getById(String s) {
        return Optional.empty();
    }

    @Override
    public BillResponseDTO update(String s, BillRequestDTO data) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

}
