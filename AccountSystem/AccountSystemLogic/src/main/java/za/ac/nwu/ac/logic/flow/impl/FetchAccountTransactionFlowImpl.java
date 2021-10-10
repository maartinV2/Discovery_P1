package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Component
public class FetchAccountTransactionFlowImpl implements FetchAccountTransactionFlow {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private final AccountTransactionTranslator accountTransactionTranslator;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;

    @Autowired
    public FetchAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator,
                                           FetchAccountTypeFlow fetchAccountTypeFlow) {
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.fetchAccountTypeFlow=fetchAccountTypeFlow;
    }

    @Override
    public List<AccountTransactionDto> getAllAccountTransactions() {

        LOGGER.info("getAllAccountTransactions endpoint reached");

        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        for(AccountTransaction accountTransaction:accountTransactionTranslator.getAllAccountTransactions()){
            accountTransactionDtos.add( new AccountTransactionDto(accountTransaction));
        }
        return accountTransactionDtos;
    }

    @Override
    public   List<AccountTransactionDto> getByMnemonic(String mnemonic){
      AccountType accountType=  fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(mnemonic);
        return accountTransactionTranslator.getByAccountType(accountType);
    }

    @Override
    public   List<AccountTransactionDto> getByMemberId(Long memberId){
        return accountTransactionTranslator.getByMemberId(memberId);
    }

    @Override
    public AccountTransactionDto getAccountTransactionDtoById(Long transactionId){
        AccountTransaction accountTransaction = accountTransactionTranslator.getAccountTransActionByPK(transactionId);
        return  null != accountTransaction ? new AccountTransactionDto(accountTransaction) :null;
    }
    @Override
    public AccountTransaction getAccountTransactionById(Long transactionId){
        AccountTransaction accountTransaction = accountTransactionTranslator.getAccountTransActionByPK(transactionId);
        return  null != accountTransaction ? accountTransaction :null;
    }

}

