package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

import java.util.List;

public interface AccountTransactionDetailsTranslator {


    AccountTransactionDetails save(AccountTransactionDetails accountTransactionDetails);
}
