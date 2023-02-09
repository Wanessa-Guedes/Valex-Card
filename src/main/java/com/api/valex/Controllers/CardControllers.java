package com.api.valex.Controllers;

import com.api.valex.Controllers.dto.CreateCardDto;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Middlewares.ErrorHandler409;
import com.api.valex.Models.TransactionType;
import com.api.valex.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/card")
public class CardControllers {

    @Autowired
    CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<Object> GetCards(@RequestHeader("x-api-key") String xApiKey,
                               @RequestBody CreateCardDto req) throws ErrorHandler404, ErrorHandler409 {
        cardService.CreateCard(xApiKey, req);

        return ResponseEntity.created(URI.create("/card")).build();
    }
}
