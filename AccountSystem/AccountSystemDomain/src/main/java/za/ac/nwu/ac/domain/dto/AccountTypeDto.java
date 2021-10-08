package za.ac.nwu.ac.domain.dto;

import  com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountType", description = "A DTO that represents the AccountType")
public class AccountTypeDto implements Serializable {


    private static final long serialVersionUID = -7731154715176897719L;

    private String mnemonic;
    private String accountTypeName;
    private LocalDate creationDate;
    private Character currency;


    public AccountTypeDto() {
    }

    public AccountTypeDto(String mnemonic, String accountTypeName, LocalDate creationDate, Character currency ) {
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.currency =currency;

    }



    //    FromDomain
    public AccountTypeDto(AccountType accountType) {
        this.setAccountTypeName(accountType.getAccountTypeName());
        this.setCreationDate(accountType.getCreationDate());
        this.setMnemonic(accountType.getMnemonic());
        this.setCurrency(accountType.getCurrency());

    }


    @ApiModelProperty(position = 1,
            value = "AccountType Mnemonic",
            name="Mnemonic",
            notes = "Uniquely identifies the account type",
            dataType = "java.lang.String",
            example = "MILES",
            required = true)
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @ApiModelProperty(position = 2,
            value = "AccountType Name",
            name="Name",
            notes = "the Name the account type",
            dataType = "java.lang.String",
            example = "Miles",
            required = true)
    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @ApiModelProperty(position = 3,
            value = "AccountType creationDate",
            name="creationDate",
            notes = "creationDate the account type",
            dataType = "java.lang.String",
            example = "2021-01-01",
            allowEmptyValue = true)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @ApiModelProperty(position = 4,
            value = "AccountType currency",
            name="currency",
            notes = "currency char",
            dataType = "java.lang.String",
            example = "R",
            allowEmptyValue = true)
    public Character getCurrency() {
        return currency;
    }

    public void setCurrency(Character currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypeDto that = (AccountTypeDto) o;
        return Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(creationDate, that.creationDate) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mnemonic, accountTypeName, creationDate, currency);
    }

    @JsonIgnore
    public AccountType ToDomain(){

        return new AccountType(getMnemonic(),getAccountTypeName(),getCreationDate(),getCurrency());
    }


    @Override
    public String toString() {
        return "AccountTypeDto{" +
                "mnemonic='" + mnemonic + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", creationDate=" + creationDate +
                ", currency=" + currency +
                '}';
    }
}