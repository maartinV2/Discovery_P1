package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.*;

import java.util.List;

@RestController
@RequestMapping("account-transaction")
public class AccountTransactionController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;
    private final CreateAccountTransactionFlow createAccountTransactionFlow;
    private final UpdateAccountTransactionFlow updateAccountTransactionFlow;
    private final DeleteAccountTransactionFlow deleteAccountTransactionFlow;



    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow,
                                        CreateAccountTransactionFlow createAccountTransactionFlow,
                                        UpdateAccountTransactionFlow updateAccountTransactionFlow,
                                        DeleteAccountTransactionFlow deleteAccountTransactionFlow) {
        this.fetchAccountTransactionFlow= fetchAccountTransactionFlow;
        this.createAccountTransactionFlow =createAccountTransactionFlow;
        this.updateAccountTransactionFlow= updateAccountTransactionFlow;
        this.deleteAccountTransactionFlow= deleteAccountTransactionFlow;
    }

    @PostMapping("")
    @ApiOperation( value = "Creates a new Transaction", notes = "Creates a new Transaction in the database")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "new Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public  ResponseEntity<GeneralResponse<AccountTransactionDto>> create(
            @ApiParam(value = "Request body to create a new Transaction", required = true)
            @RequestBody AccountTransactionDto accountTransactionDto){
        System.out.println("1 Controller");
        AccountTransactionDto accountTransactionsResponse = createAccountTransactionFlow.create(accountTransactionDto);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse<>(true ,accountTransactionsResponse);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/AllAccountTransactions")
    @ApiOperation(value="Gets all Transactions." , notes="Returns a List of all transactions that took place")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Transactions returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAllAccountTypes(){
        System.out.println("1 Controller");
        List<AccountTransactionDto> accountTransactions= fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response= new GeneralResponse<>(true,accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{transactionId}")
    @ApiOperation( value = "Updates the specified transaction", notes = "Updates existing transaction in db")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Updated transaction updated", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public   ResponseEntity<GeneralResponse<AccountTransactionDto>> update(
            @ApiParam(value = "The mnemonic that uniquely identifies the transaction.",
                    example = "50001",
                    name = "transactionId",
                    required = true)
            @PathVariable("transactionId" ) final Long transactionId,
            @ApiParam(value = "Request body to create a new Transaction", required = true)
            @RequestBody AccountTransactionDto accountTransactionDto)
    {
        int accountTypeResponse = updateAccountTransactionFlow.update(accountTransactionDto,transactionId);
        GeneralResponse<AccountTransactionDto> response = new GeneralResponse(true ,accountTypeResponse);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping ("/{transactionId}")
    @ApiOperation(value="Deletes the configured Account type that matches the transactionId in the url." , notes="Returns account type")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })

    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> deleteAccountTransactionByAccountId(
            @ApiParam(value = "The transactionId that uniquely identifies the AccountTransaction.",
                    example = "50018",
                    name = "transactionId",
                    required = true)
            @PathVariable("transactionId") final Long transactionId){

        int payload= deleteAccountTransactionFlow.deleteAccountTransactionByTransactionId(transactionId);
        GeneralResponse<List<AccountTypeDto>> response= new GeneralResponse(true,payload);
        return new ResponseEntity(response, HttpStatus.OK);
    }



    @GetMapping("/{mnemonic}")
    @ApiOperation(value="Gets the configured AccountTransactions that matches the mnemonic in the url." , notes="Returns account type")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Account type returned", response = GeneralResponse.class),
            @ApiResponse(code=400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code=404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code=500, message = "Internal Server Error", response = GeneralResponse.class)
    })

    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAccountTypeByMnemonic(
            @ApiParam(value = "The mnemonic that uniqyely identifies the AccountTransaction.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic) {

        List<AccountTransactionDto> accountTransactions=  fetchAccountTransactionFlow.getByMnemonic(mnemonic);
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse(true, accountTransactions);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
