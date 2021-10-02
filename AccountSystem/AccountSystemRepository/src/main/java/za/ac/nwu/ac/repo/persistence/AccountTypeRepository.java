package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Transactional
    @Query("SELECT  at FROM  AccountType  at WHERE at.mnemonic = :mnemonic")
    AccountType findByMnemonic(String mnemonic);


    @Transactional
    @Modifying
    @Query("DELETE FROM AccountType at WHERE at.mnemonic = :mnemonic")
    int deleteAccountTypeByMnemonic(String mnemonic);

    @Modifying
    @Query(value = "UPDATE AccountType at SET at.accountTypeName = :accountTypeName, at.creationDate= :creationDate  WHERE at.mnemonic = :mnemonic")
    int  updateByMnemonic(String mnemonic, String accountTypeName, LocalDate creationDate);


}
