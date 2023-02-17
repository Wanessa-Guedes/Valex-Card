package com.api.valex.Controllers;

import com.api.valex.Controllers.dto.ActivCardDto;
import com.api.valex.Controllers.dto.BlockCardDto;
import com.api.valex.Controllers.dto.CardGetDto;
import com.api.valex.Controllers.dto.CreateCardDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Middlewares.ErrorHandler409;
import com.api.valex.Models.Cards;
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

    @PostMapping("/activate")
    public ResponseEntity<Object> ActivateCards(@RequestBody ActivCardDto req) throws ErrorHandler400, ErrorHandler404 {
        cardService.ActivateCard(req);

        return ResponseEntity.ok().build();
    }

    // PULAR ESSA PARA DEPOIS DO RECARGA E COMPRAS (PENSAR EM COMO MONTAR O OBJETO)
    @GetMapping("/infos/{id}")
    public ResponseEntity<CardGetDto> GetInfosCard(@PathVariable(value = "id") long id) throws ErrorHandler404 {
        CardGetDto card = cardService.GetTransactions(id);
        return ResponseEntity.ok().body(card);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<Object> BlockCard(@PathVariable(value = "id") long id,
                                            @RequestBody BlockCardDto password) throws ErrorHandler404, ErrorHandler400 {
        cardService.BlockCard(id, password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unblock/{id}")
    public ResponseEntity<Object> UnBlockCard(@PathVariable(value = "id") long id,
                                            @RequestBody BlockCardDto password) throws ErrorHandler404, ErrorHandler400 {
        cardService.UnBlockCard(id, password);
        return ResponseEntity.ok().build();
    }
}
