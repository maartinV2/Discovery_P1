package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDto> getAllAccountTypes();
   AccountTypeDto  getAccountTypeByMnemonic(String mnemonic);
   AccountType  getAccountTypeDbEntityByMnemonic(String mnemonic);

    AccountTypeDto create(AccountTypeDto accountType);

    int deleteAccountTypeByMnemonic(String mnemonic);

    int update(AccountTypeDto accountTypeDto,String mnemonic);
}
