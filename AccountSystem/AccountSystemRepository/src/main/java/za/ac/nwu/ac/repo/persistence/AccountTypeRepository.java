package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Modifying
    @Query(value = "UPDATE AccountType a SET ACCOUNT_TYPE_NAME= :accountTypeName, CREATION_DATE= :creationDate  WHERE MNEMONIC = :mnemonic")
    int  updateByMnemonic(String mnemonic, String accountTypeName, LocalDate creationDate);


}
