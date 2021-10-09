package za.ac.nwu.ac.logic.flow;


import za.ac.nwu.ac.domain.dto.AccountTypeDto;



public interface UpdateAccountTypeFlow {
    int update(AccountTypeDto accountType, String mnemonic);
}
