package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.DeleteAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

@Transactional
@Component("deleteAccountTypeFlowName")
public class DeleteAccountTypeFlowImpl implements DeleteAccountTypeFlow {

    private  final AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public  DeleteAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator =accountTypeTranslator;
    }

    @Override
    public int deleteAccountTypeByMnemonic(String mnemonic){
        return accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
    }
    public  boolean methodToTest(){
        return  true;
    }
}

