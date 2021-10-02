package za.ac.nwu.ac.translator.Impl;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {

    private  final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }


    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try {
            for (AccountType accountType : accountTypeRepository.findAll()) {
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from DB ", e);

        }
        return  accountTypeDtos;
    }
    @Override
    public  AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        AccountTypeDto   accountTypeDto= new AccountTypeDto();

        try {

            AccountType accountType  = accountTypeRepository.findByMnemonic(mnemonic) ;
              accountTypeDto= new AccountTypeDto(accountType);

        } catch (Exception e) {
            throw new RuntimeException("Unable to read from DB ", e);

        }
        return  accountTypeDto;
    }

    @Override
    public  int deleteAccountTypeByMnemonic(String mnemonic) {

        try {

            int response  = accountTypeRepository.deleteAccountTypeByMnemonic(mnemonic);
            return  response;

        } catch (Exception e) {
            throw new RuntimeException("Unable to read from DB ", e);

        }

    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto){
        try {
            AccountType accountType = accountTypeRepository.save(accountTypeDto.ToDomain());

            return  new AccountTypeDto(accountType);
            }
        catch ( Exception e){
            throw new RuntimeException("Unable to write to DB ", e);
        }

    }
    @Override
    public int  update(AccountTypeDto accountTypeDto, String mnemonic){
        try {

            String name =accountTypeDto.ToDomain().getAccountTypeName();
            LocalDate date=accountTypeDto.ToDomain().getCreationDate();

             accountTypeRepository.updateByMnemonic(mnemonic,name,date);

            return  200;
        }
        catch ( Exception e){
            throw new RuntimeException("Unable to update to DB ", e);
        }

    }



}
