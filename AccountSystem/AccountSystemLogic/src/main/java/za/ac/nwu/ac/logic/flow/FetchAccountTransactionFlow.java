package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface FetchAccountTransactionFlow {
    List<AccountTransactionDto> getAllAccountTransactions();
    List<AccountTransactionDto> getByMnemonic(String mnemonic);
    AccountTransactionDto getAccountTransactionDtoById(Long transactionId);
    AccountTransaction getAccountTransactionById(Long transactionId);
}
