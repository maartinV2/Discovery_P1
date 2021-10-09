package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;



@Transactional(propagation = Propagation.REQUIRED)
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
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto){
        accountTransactionDto.setTransactionId(null);

        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(
                accountTransactionDto.getMnemonic());
        AccountTransaction accountTransaction =accountTransactionDto.ToDomain(accountType);

        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);

        if(null != accountTransactionDto.getDetailsDto()) {
          AccountTransactionDetails accountTransactionDetails =accountTransactionDto.getDetailsDto().
                  ToDomain(createdAccountTransaction);
            accountTransactionDetailsTranslator.save(accountTransactionDetails);
      }
         return new AccountTransactionDto();
    }


//    @Override
//    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto){
//        accountTransactionDto.setTransactionId(null);
//        System.out.println("2 CREATETRANSACTIONFLOW");
//        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(accountTransactionDto.getMnemonic());
//        AccountTransaction accountTransaction =accountTransactionDto.ToDomain(accountType);
//        System.out.println("3 CREATETRANSACTIONFLOW ACCOUNTTYPE"+ accountType);
//        //Case 5 with cascade dont save mannully link both ways
//      if(null != accountTransactionDto.getDetailsDto()) {
//          AccountTransactionDetails accountTransactionDetails =accountTransactionDto.getDetailsDto().ToDomain(accountTransaction);
//          accountTransaction.setDetails(accountTransactionDetails);
//      }
//        System.out.println("4 CREATE TRANSACTIONFLOW ACCOUNTTYPE details"+ accountTransaction.getDetails());
//      AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);
//
//
//
//         return new AccountTransactionDto();
//    }

}



