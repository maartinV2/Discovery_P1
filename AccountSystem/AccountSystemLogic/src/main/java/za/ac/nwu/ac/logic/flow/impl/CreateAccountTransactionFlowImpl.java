package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

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

        LOGGER.info("TRANSACTION INFO RECEIVED: {}",accountTransactionDto);

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




}



