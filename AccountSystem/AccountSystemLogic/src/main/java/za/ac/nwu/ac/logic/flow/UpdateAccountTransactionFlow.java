package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;


public interface UpdateAccountTransactionFlow {
   int update(AccountTransactionDto accountTransactionDto, Long transactionId);
}
