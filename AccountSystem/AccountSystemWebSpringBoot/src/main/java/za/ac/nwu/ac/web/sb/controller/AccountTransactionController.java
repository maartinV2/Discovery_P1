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



    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow) {
        this.fetchAccountTransactionFlow= fetchAccountTransactionFlow;
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
        List<AccountTransactionDto> accountTransactions= fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response= new GeneralResponse<>(true,accountTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}