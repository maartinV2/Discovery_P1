package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface AccountTransactionTranslator {

    List<AccountTransaction> getAllAccountTransactions();

    AccountTransaction getAccountTransActionByPK(Long transactionId);

    AccountTransaction save(AccountTransaction accountTransaction);

    int update(AccountTransaction accountTransaction, Long transactionId);

    int deleteAccountTypeByTransactionId(Long transactionId);

    List<AccountTransactionDto> getByAccountType(AccountType accountType);

    List<AccountTransactionDto> getByMemberId(Long memberId);
}




