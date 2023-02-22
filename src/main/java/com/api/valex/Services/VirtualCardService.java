package com.api.valex.Services;

import com.api.valex.Controllers.dto.VirtualCardCreateDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Models.Cards;
import com.api.valex.Repositories.CardRepository;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static com.api.valex.Middlewares.Crypt.crypt;

@Service
public class VirtualCardService {

    @Autowired
    CardRepository cardRepository;

    public void CreateVirtual(VirtualCardCreateDto req) throws ErrorHandler400, ErrorHandler404 {
        Cards card = cardRepository.findBySecurityCode(req.getSecurityCode());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }

        if(card.getPassword() == null){
            throw new ErrorHandler400("400", "Cartão não ativado");
        }
        String cryptPass = crypt(req.getPassword());
        if(!card.getPassword().equals(cryptPass)){
            throw new ErrorHandler400("400", "Senha incorreta");
        }
        if(card.getVirtual()){
            throw new ErrorHandler400("400", "Não pode vincular a um cartão virtual");
        }

        Cards virtualCard = new Cards();

        Faker fake = new Faker();
        virtualCard.setEmployee(card.getEmployee());
        virtualCard.setNumber(fake.finance().creditCard(CreditCardType.MASTERCARD));
        virtualCard.setCardHolderName(card.getEmployee().getFullname());
        virtualCard.setSecurityCode(String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000)));
        virtualCard.setExpirationDate(LocalDate.now().plusYears(5));
        virtualCard.setVirtual(true);
        virtualCard.setBlocked(false);
        virtualCard.setType(card.getType());
        virtualCard.setPassword(cryptPass);
        cardRepository.save(virtualCard);
    }
}
