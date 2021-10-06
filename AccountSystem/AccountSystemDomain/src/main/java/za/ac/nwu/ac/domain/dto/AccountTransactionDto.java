package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountTransaction", description = "A DTO that represents the AccountTransaction")
public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = -7819344808062462808L;

    private Long transactionId;
    private String mnemonic;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;

    public AccountTransactionDto() {

    }

    public AccountTransactionDto(Long transactionId, String mnemonic, Long memberId, Long amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.mnemonic = mnemonic;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    //FromDomain
    public AccountTransactionDto(AccountTransaction accountTransaction) {
        this.setTransactionId(accountTransaction.getTransactionId());
        this.setMemberId(accountTransaction.getMemberId());
        this.setAccountType(accountTransaction.getAccountType().getMnemonic());
        this.setAmount(accountTransaction.getAmount());
        this.setTransactionDate(accountTransaction.getTransactionDate());


    }

    @ApiModelProperty(position = 1,
            value = "AccountTransaction Id",
            name = "Id",
            notes = "Uniquely identifies the account transaction",
            dataType = "java.lang.String",
            example = "100000000000000014",
            required = true)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }


    @ApiModelProperty(position = 2,
            value = "AccountType Object",
            name = "AccountType",
            notes = "Uniquely identifies the account type",
            dataType = "java.lang.String",
            example = "AccountType{accountTypeId=100000000000000017, mnemonic='MILES', accountTypeName='Miles', creationDate=2021-09-09}",
            required = true)
    public String getAccountType() {
        return mnemonic;
    }

    public void setAccountType(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @ApiModelProperty(position = 3,
            value = "AccountTransaction MemberId",
            name = "Id",
            notes = "Uniquely identifies what member accountTransaction belongs to ",
            dataType = "java.lang.String",
            example = "100000000000000014",
            required = true)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @ApiModelProperty(position = 4,
            value = "Amount of MILES, BUCKS , PLAYS , RAND",
            name = "Id",
            notes = "Uniquely identifies amount",
            dataType = "java.lang.String",
            example = "200",
            required = true)
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @ApiModelProperty(position = 5,
            value = "AccountTransaction Date",
            name = "AccountTransactionDate",
            notes = "Date the account transaction took place",
            dataType = "java.lang.String",
            example = "2021-01-01",
            allowEmptyValue = true)
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @JsonIgnore
    public AccountTransaction ToDomain() {

        return new AccountTransaction(getTransactionId(), getMemberId(), getAmount(), getTransactionDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(mnemonic, that.mnemonic) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, mnemonic, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "transactionId=" + transactionId +
                ", mnemonic='" + mnemonic + '\'' +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}

