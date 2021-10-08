package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import  za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.DeleteAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.UpdateAccountTypeFlow;


import java.util.List;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;
    private final UpdateAccountTypeFlow updateAccountTypeFlow;
    private final DeleteAccountTypeFlow deleteAccountTypeFlow;


    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow,
               @Qualifier("createAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow,
               @Qualifier("updateAccountTypeFlowName") UpdateAccountTypeFlow updateAccountTypeFlow,
               @Qualifier("deleteAccountTypeFlowName") DeleteAccountTypeFlow deleteAccountTypeFlow) {
        this.fetchAccountTypeFlow= fetchAccountTypeFlow;
        this.createAccountTypeFlow = createAccountTypeFlow;
        this.updateAccountTypeFlow = updateAccountTypeFlow;
        this.deleteAccountTypeFlow = deleteAccountTypeFlow;
    }



    @GetMapping("/AllAccountTypes")
    @ApiOperation(value="Gets all the configured Account types." , notes="Returns a List of account types")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAllAccountTypes(){
        List<AccountTypeDto> accountTypes= fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response= new GeneralResponse<>(true,accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{mnemonic}")
    @ApiOperation(value="Gets the configured Account type that matches the mnemonic in the url." , notes="Returns account type")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })

    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAccountTypeByMnemonic(
        @ApiParam(value = "The mnemonic that uniqyely identifies the AccountType.",
                example = "MILES",
                name = "mnemonic",
                required = true)
        @PathVariable("mnemonic") final String mnemonic){

        AccountTypeDto accountType= fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        GeneralResponse<List<AccountTypeDto>> response= new GeneralResponse(true,accountType);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @DeleteMapping ("/{mnemonic}")
    @ApiOperation(value="Deletes the configured Account type that matches the mnemonic in the url." , notes="Returns account type")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })

    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> deleteAccountTypeByMnemonic(
            @ApiParam(value = "The mnemonic that uniqyely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic){

        int payload= deleteAccountTypeFlow.deleteAccountTypeByMnemonic(mnemonic);
        GeneralResponse<List<AccountTypeDto>> response= new GeneralResponse(true,payload);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation( value = "Creates a new AccountType", notes = "Creates a new accountType in the database")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "new Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public  ResponseEntity<GeneralResponse<AccountTypeDto>> create(
            @ApiParam(value = "Request body to create a new AccountType", required = true)
            @RequestBody AccountTypeDto accountTypeDto){
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountTypeDto);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true ,accountTypeResponse);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PutMapping("/{mnemonic}")
    @ApiOperation( value = "Updates the specified AccountType", notes = "Updates existing AccountType in db")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Updated AccountType returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public   ResponseEntity<GeneralResponse<AccountTypeDto>> update(
            @ApiParam(value = "The mnemonic that uniqyely identifies the AccountType.",
            example = "MILES",
            name = "mnemonic",
            required = true)
            @PathVariable("mnemonic" ) final String mnemonic,
            @ApiParam(value = "Request body to create a new AccountType", required = true)
            @RequestBody AccountTypeDto accountTypeDto)
          {
        int accountTypeResponse = updateAccountTypeFlow.update(accountTypeDto,mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse(true ,accountTypeResponse);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
