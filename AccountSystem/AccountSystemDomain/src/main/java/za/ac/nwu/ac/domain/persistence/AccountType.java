package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "ACCOUNT_TYPE", schema = "CMPG323")
public class AccountType implements Serializable   {

    private static final long serialVersionUID = 4453290124401196523L;


    private Long accountTypeId;
    private String mnemonic;
    private String accountTypeName;
    private  LocalDate creationDate;
    private  Character currency;

    private Set<AccountTransaction> accountTransactions;

    public AccountType() {
    }

    public AccountType(Long accountTypeId, String mnemonic, String accountTypeName, LocalDate creationDate, Character currency) {
        this.accountTypeId = accountTypeId;
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.currency =currency;

    }

    public AccountType(String mnemonic, String accountTypeName, LocalDate creationDate, Character currency) {
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.currency =currency;
    }


    @Id
    @SequenceGenerator(name = "DISCOVERY_12_SEQ", sequenceName = "CMPG323.DISCOVERY_12_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DISCOVERY_12_SEQ")
    @Column(name = "ACCOUNT_TYPE_ID")
    public Long getAccountTypeId() {return accountTypeId;  }

    @Column(name="MNEMONIC")
    public String getMnemonic() {
        return mnemonic;
    }

    @Column(name = "ACCOUNT_TYPE_NAME")
    public String getAccountTypeName() {
        return accountTypeName;
    }

    @Column(name="CREATION_DATE")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Column(name="CURRENCY")
    public Character getCurrency() {return currency;}

    @OneToMany(targetEntity = AccountTransaction.class,fetch = FetchType.LAZY,mappedBy = "accountType")
    public Set<AccountTransaction> getAccountTransActions(){
        return accountTransactions;
    }



    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }
    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setCurrency(Character currency) {this.currency = currency;}

    public void setAccountTransActions(Set<AccountTransaction> accountTransactions){
        this.accountTransactions=accountTransactions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(creationDate, that.creationDate) && Objects.equals(currency, that.currency) && Objects.equals(accountTransactions, that.accountTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeId, mnemonic, accountTypeName, creationDate, currency, accountTransactions);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeId=" + accountTypeId +
                ", mnemonic='" + mnemonic + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", creationDate=" + creationDate +
                ", currency=" + currency +
                ", accountTransactions=" + accountTransactions +
                '}';
    }
}
