package za.ac.nwu.ac.translator.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.repo.persistence.AccountTransactionDetailsRepository;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;

import java.util.List;


@Component
public class AccountTransactionDetailsTranslatorImpl implements AccountTransactionDetailsTranslator {

    private  final AccountTransactionDetailsRepository accountTransactionDetailsRepository;

    @Autowired
    public AccountTransactionDetailsTranslatorImpl(AccountTransactionDetailsRepository accountTransactionDetailsRepository){
        this.accountTransactionDetailsRepository = accountTransactionDetailsRepository;
    }

    @Override
    public AccountTransactionDetails save(AccountTransactionDetails accountTransactionDetails) {

        try{
            return accountTransactionDetailsRepository.save(accountTransactionDetails);
        }
        catch ( Exception e){
            throw new RuntimeException("Unable to save in DB ", e);
        }
    }


}
