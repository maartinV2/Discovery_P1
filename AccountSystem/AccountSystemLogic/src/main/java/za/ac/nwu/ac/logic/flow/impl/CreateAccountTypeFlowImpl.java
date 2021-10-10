package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Transactional(propagation = Propagation.REQUIRED)
@Component("createAccountTypeFlowName")
public class CreateAccountTypeFlowImpl implements CreateAccountTypeFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private  final AccountTypeTranslator accountTypeTranslator;

    public CreateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator =accountTypeTranslator;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountType){

        LOGGER.info("TYPE INFO RECEIVED: {}",accountType);

        if(null == accountType.getCreationDate()){
            accountType.setCreationDate(LocalDate.now());
        }
        return accountTypeTranslator.create(accountType);
    }
}

