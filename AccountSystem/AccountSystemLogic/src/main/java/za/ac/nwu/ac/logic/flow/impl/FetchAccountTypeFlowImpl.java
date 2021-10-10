package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;


import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Component
public class FetchAccountTypeFlowImpl implements FetchAccountTypeFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private  final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public  FetchAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator =accountTypeTranslator;
    }
    @Override
    public  List<AccountTypeDto> getAllAccountTypes(){
        LOGGER.info("get all account types endpoint reached");

        return accountTypeTranslator.getAllAccountTypes();
}

    @Override
    public  AccountTypeDto getAccountTypeByMnemonic(String mnemonic){
        LOGGER.info("get all account types byMnemonic endpoint reached : {}",mnemonic);
        return accountTypeTranslator.getAccountTypeByMnemonic(mnemonic);
    }
    @Override
    public AccountType getAccountTypeDbEntityByMnemonic(String mnemonic){
        return accountTypeTranslator.getAccountTypeDbEntityByMnemonic(mnemonic);
    }
public  boolean methodToTest(){
        return  true;
}
}
