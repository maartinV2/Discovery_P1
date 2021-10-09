package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.util.List;

public interface AccountTransactionTranslator {

    List<AccountTransaction> getAllAccountTransactions();

    AccountTransaction getAccountTransActionByPK(Long transactionId);

    AccountTransaction save(AccountTransaction accountTransaction);

    int update(AccountTransaction accountTransaction, Long transactionId);

    int deleteAccountTypeByTransactionId(Long transactionId);
}




