package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private final AccountTransactionTranslator accountTransactionTranslator;
    private final AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator,AccountTransactionDetailsTranslator accountTransactionDetailsTranslator,FetchAccountTypeFlow fetchAccountTypeFlow) {
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.accountTransactionDetailsTranslator = accountTransactionDetailsTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
    }

    @Override
    public AccountTransactionDto save(AccountTransactionDto accountTransactionDto){
        accountTransactionDto.setTransactionId(null);
        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(accountTransactionDto.getMnemonic());
        AccountTransaction accountTransaction =accountTransactionDto.ToDomain(accountType);

        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);
        System.out.println(createdAccountTransaction);

        if(null != accountTransactionDto.getDetailsDto()){
            AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetailsDto().ToDomain(createdAccountTransaction);
            System.out.println(accountTransactionDetails);
            accountTransactionDetailsTranslator.save(accountTransactionDetails);
        }


        if(null == accountTransactionDto.getTransactionDate()){
            accountTransactionDto.setTransactionDate(LocalDate.now());
        }
         return new AccountTransactionDto();
    }

}



