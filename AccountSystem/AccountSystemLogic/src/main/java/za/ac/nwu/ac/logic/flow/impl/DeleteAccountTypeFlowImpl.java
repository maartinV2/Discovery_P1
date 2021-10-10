package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.logic.flow.DeleteAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

@Transactional(propagation = Propagation.REQUIRED)
@Component("deleteAccountTypeFlowName")
public class DeleteAccountTypeFlowImpl implements DeleteAccountTypeFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private  final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public  DeleteAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator =accountTypeTranslator;
    }

    @Override
    public int deleteAccountTypeByMnemonic(String mnemonic){
        LOGGER.info("DELETE Type INFO mnemonic RECEIVED: {}",mnemonic);

        return accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
    }
    public  boolean methodToTest(){
        return  true;
    }
}

