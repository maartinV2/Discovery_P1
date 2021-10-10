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
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.UpdateAccountTransactionFlow;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;


@Transactional(propagation = Propagation.REQUIRED)
@Component("UpdateTransactionFlow")
public class UpdateAccountTransactionFlowImpl implements UpdateAccountTransactionFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private final AccountTransactionTranslator accountTransactionTranslator;
    private final AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;

    public UpdateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator, AccountTransactionDetailsTranslator accountTransactionDetailsTranslator, FetchAccountTypeFlow fetchAccountTypeFlow,FetchAccountTransactionFlow fetchAccountTransactionFlow) {
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.accountTransactionDetailsTranslator = accountTransactionDetailsTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.fetchAccountTransactionFlow =fetchAccountTransactionFlow;
    }

    @Override
    public int update(AccountTransactionDto accountTransactionDto, Long transactionId ){

        LOGGER.info("update transaction input info transactionId: {}",transactionId);

        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(
                accountTransactionDto.getMnemonic());
        AccountTransaction accountTransaction =accountTransactionDto.ToDomain(accountType);
        accountTransaction.setTransactionId(transactionId);
        int response = accountTransactionTranslator.update(accountTransaction,transactionId);

    if (response==200) {

            if(null != accountTransactionDto.getDetailsDto()) {

                AccountTransactionDetails accountTransactionDetails =accountTransactionDto.getDetailsDto().
                        ToDomain(accountTransaction);
                accountTransactionDetailsTranslator.update(accountTransactionDetails);
            }
    }
        return 204;
    }



}



