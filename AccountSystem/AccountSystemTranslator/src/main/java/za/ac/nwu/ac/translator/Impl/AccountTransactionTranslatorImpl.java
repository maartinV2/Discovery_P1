package za.ac.nwu.ac.translator.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.repo.persistence.AccountTransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTransactionTranslatorImpl implements AccountTransactionTranslator {

    private  final AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public AccountTransactionTranslatorImpl(AccountTransactionRepository accountTransactionRepository){
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<AccountTransaction> getAllAccountTransactions() {
        List<AccountTransaction> accountTransactions = new ArrayList<>();
        try {
            for ( AccountTransaction accountTransaction : accountTransactionRepository.findAll()) {
                accountTransactions.add(accountTransaction);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from DB ", e);

        }
        return  accountTransactions;
    }

    @Override
    public AccountTransaction getAccountTransActionByPK(Long PK ) {
        try {
            return  accountTransactionRepository.findById(PK).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from DB ", e);
        }

    }

    @Override
    public AccountTransaction save(AccountTransaction accountTransaction) {

        try{
            return accountTransactionRepository.save(accountTransaction);
        }
        catch ( Exception e){
            throw new RuntimeException("Unable to save in DB ", e);
        }
    }




}
