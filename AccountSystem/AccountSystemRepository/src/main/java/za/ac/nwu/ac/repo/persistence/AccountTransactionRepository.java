package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.time.LocalDate;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {


}
