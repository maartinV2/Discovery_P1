package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

import java.time.LocalDate;

public interface AccountTransactionDetailsRepository extends JpaRepository<AccountTransactionDetails, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AccountTransactionDetails atd SET atd.accountTransaction.transactionId= :transactionId,atd.numberOfItems=:numberOfItems,atd.partnerName=:partnerName WHERE atd.accountTransactionDetailsId = :accountTransactionDetailsId")
    int updateByTransactionDetailsId(Long transactionId,Long numberOfItems, String partnerName,Long accountTransactionDetailsId );

    @Transactional
    @Modifying
    @Query("UPDATE AccountTransactionDetails atd SET atd.numberOfItems=:numberOfItems,atd.partnerName=:partnerName WHERE atd.accountTransaction.transactionId= :transactionId")
    int updateByTransactionId(Long transactionId,Long numberOfItems, String partnerName);



}
