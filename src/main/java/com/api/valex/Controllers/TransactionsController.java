package com.api.valex.Controllers;

import com.api.valex.Controllers.dto.PaymentCardDto;
import com.api.valex.Controllers.dto.ReechargeCardDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/recharge")
    public ResponseEntity<Object> RechargeCard(@RequestHeader("x-api-key") String xApiKey,
                                               @RequestBody ReechargeCardDto req) throws ErrorHandler404, ErrorHandler400 {
        transactionsService.RechargeCard(xApiKey, req);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/payment")
    public ResponseEntity<Object> PaymentCard(@RequestBody PaymentCardDto req) throws ErrorHandler404, ErrorHandler400 {
        transactionsService.PaymentCard(req);
        return ResponseEntity.ok().build();
    }
}
