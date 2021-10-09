package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AccountTransaction t SET t.accountType.accountTypeId= :accountTypeId, t.memberId= :memberId , t.transactionDate= :transactionDate, t.amount= :amount   WHERE t.transactionId = :transactionId")
    int updateByTransactionId(Long transactionId,Long accountTypeId, Long memberId, Number amount, LocalDate transactionDate);



    @Transactional
    @Modifying
    @Query("DELETE FROM AccountTransaction t WHERE t.transactionId = :transactionId")
    int deleteAccountTypeByTransactionId(Long transactionId);
}
