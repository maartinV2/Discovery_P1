package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.logic.flow.DeleteAccountTransactionFlow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

@Transactional(propagation = Propagation.REQUIRED)
@Component("deleteAccountTransactionFlow")
public class DeleteAccountTransactionFlowImpl implements DeleteAccountTransactionFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private  final AccountTransactionTranslator accountTypeTranslator;
    private final AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;

    @Autowired
    public DeleteAccountTransactionFlowImpl(AccountTransactionTranslator accountTypeTranslator,
                                            AccountTransactionDetailsTranslator accountTransactionDetailsTranslator){
    this.accountTypeTranslator=accountTypeTranslator;
    this.accountTransactionDetailsTranslator =accountTransactionDetailsTranslator;
    }

    @Override
    public int deleteAccountTransactionByTransactionId(Long transactionId){
        LOGGER.info("DELETE TRANSACTION INFO Id RECEIVED: {}",transactionId);

        accountTransactionDetailsTranslator.deleteByTransactionId(transactionId);
        return accountTypeTranslator.deleteAccountTypeByTransactionId(transactionId);
    }
    public  boolean methodToTest(){
        return  true;
    }
}

