package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.UpdateAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.time.LocalDate;

@Transactional(propagation = Propagation.REQUIRED)
@Component("updateAccountTypeFlowName")
public class UpdateAccountTypeFlowImpl implements UpdateAccountTypeFlow {
    private  final AccountTypeTranslator accountTypeTranslator;

    public UpdateAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator){
        this.accountTypeTranslator =accountTypeTranslator;
    }

    @Override
    public int update(AccountTypeDto accountType, String mnemonic) {

            if (null == accountType.getCreationDate()) {
                accountType.setCreationDate(LocalDate.now());
            }
        return  accountTypeTranslator.update(accountType,mnemonic);

        }
}



