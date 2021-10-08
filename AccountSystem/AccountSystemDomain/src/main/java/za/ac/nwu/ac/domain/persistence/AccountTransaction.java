package za.ac.nwu.ac.domain.persistence;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;

import javax.persistence.*;
import java.io.Serializable;
import  java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "ACCOUNT_TX", schema = "CMPG323")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = -3951961943090087136L;

    private  Long transactionId;
    private  AccountType accountType;
    private  Long memberId;
    private  Double amount;
    private  LocalDate transactionDate;

    private  AccountTransactionDetails details;


    public AccountTransaction() {
    }


    public AccountTransaction(Long transactionId, AccountType accountType, Long memberId, Double amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    @Id
    @SequenceGenerator(name = "ACCOUNT_TX_ID_SEQ", sequenceName = "CMPG323.ACCOUNT_TX_ID_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ACCOUNT_TX_ID_SEQ")
    @Column(name = "ACCOUNT_TX_ID")
    public Long getTransactionId() {
        return transactionId;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    @Column(name = "AMOUNT")
    public Double getAmount() {
        return amount;
    }

    @Column(name = "TX_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType(){
        return accountType;
    }


    @OneToOne(targetEntity = AccountTransactionDetails.class, fetch = FetchType.LAZY,mappedBy = "accountTransaction",orphanRemoval = true,cascade = CascadeType.PERSIST)
    public AccountTransactionDetails getDetails() {return details;}



    public void setTransactionId(Long transactionId) { this.transactionId = transactionId;}
    public void setAccountType(AccountType accountType){
        this.accountType=accountType;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public void setAmount(Double amount) {this.amount = amount;}
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void setDetails(AccountTransactionDetails details) {
        this.details = details;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountType, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountType=" + accountType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
