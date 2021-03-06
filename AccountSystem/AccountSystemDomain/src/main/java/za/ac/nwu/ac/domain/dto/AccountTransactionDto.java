package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
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
    private AccountTransactionDetailsDto detailsDto;

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
        this.transactionId = accountTransaction.getTransactionId();
        this.mnemonic =accountTransaction.getAccountType().getMnemonic();
        this.memberId = accountTransaction.getMemberId();
        this.amount = accountTransaction.getAmount();
        this.transactionDate = accountTransaction.getTransactionDate();
        System.out.println( "3 DTO AccountTransactionID = "+ this.transactionId );
       if(null!=accountTransaction.getDetails()){
           this.detailsDto = new AccountTransactionDetailsDto(accountTransaction.getDetails());
           System.out.println( this.detailsDto);
       }

    }



    @ApiModelProperty(position = 1,
            value = "AccountTransaction Id",
            name = "Id",
            notes = "Uniquely identifies the account transaction",
            dataType = "java.lang.String",
            example = "11",
            required = true)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @ApiModelProperty(position = 2,
            value = "AccountType Mnemonic",
            name = "Id",
            notes = "Uniquely identifies what AccountType the Transaction is",
            dataType = "java.lang.String",
            example = "MILES",
            required = true)
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @ApiModelProperty(position = 3,
            value = "AccountTransaction MemberId",
            name = "Id",
            notes = "Uniquely identifies what member accountTransaction belongs to ",
            dataType = "java.lang.String",
            example = "114",
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


    public AccountTransactionDetailsDto getDetailsDto() {
        return detailsDto;
    }

    public void setDetailsDto(AccountTransactionDetailsDto detailsDto) {
        this.detailsDto = detailsDto;
    }



    @JsonIgnore
    public AccountTransaction ToDomain(AccountType accountType) {
        return  new AccountTransaction(this.getTransactionId(),accountType,this.getMemberId(),this.getAmount(),this.getTransactionDate());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDto that = (AccountTransactionDto) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(mnemonic, that.mnemonic) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(detailsDto, that.detailsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, mnemonic, memberId, amount, transactionDate, detailsDto);
    }

    @Override
    public String toString() {
        return "AccountTransactionDto{" +
                "transactionId=" + transactionId +
                ", mnemonic='" + mnemonic + '\'' +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", detailsDto=" + detailsDto +
                '}';
    }
}

