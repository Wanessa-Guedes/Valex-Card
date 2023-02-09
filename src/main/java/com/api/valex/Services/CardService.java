package com.api.valex.Services;

import com.api.valex.Controllers.dto.CreateCardDto;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Middlewares.ErrorHandler409;
import com.api.valex.Models.Cards;
import com.api.valex.Models.Companies;
import com.api.valex.Models.Employees;
import com.api.valex.Models.TransactionType;
import com.api.valex.Repositories.CardRepository;
import com.api.valex.Repositories.CompaniesRepository;
import com.api.valex.Repositories.EmployeeRepsoitory;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CompaniesRepository companiesRepository;

    @Autowired
    EmployeeRepsoitory employeeRepsoitory;

    public void CreateCard(String xApiKey, CreateCardDto req) throws ErrorHandler404, ErrorHandler409 {
        Companies company = companiesRepository.findByApiKey(xApiKey);
        if(company == null){
            throw new ErrorHandler404("404", "Empresa não registrada");
        }
        Employees employee = employeeRepsoitory.findById(req.getEmployeeId()).orElse(null);
        if(employee == null){
            throw new ErrorHandler404("404", "Funcionário não registrado");
        }

        if(!EnumUtils.isValidEnum(TransactionType.class, req.getType().toString())){
            throw new ErrorHandler404("404", "Tipo de cartão não registrado");
        }

        List<Cards> cards = cardRepository.findAllByEmployeeId(employee.getId());
        for (Cards card : cards) {
            if (card.getType() == req.getType()) {
                throw new ErrorHandler409("409", "Esse colaborador já possue um cartão ativo desse tipo");
            }
        }

        Cards newCard = new Cards();
        Faker fake = new Faker();
        newCard.setEmployee(employee);
        newCard.setNumber(fake.finance().creditCard(CreditCardType.VISA));
        newCard.setCardHolderName(employee.getFullname());
        newCard.setSecurityCode(fake.finance().iban());
        newCard.setExpirationDate(String.valueOf(LocalDate.now().plusYears(5)));
        newCard.setVirtual(false);
        newCard.setBlocked(false);
        newCard.setType(req.getType());

        cardRepository.save(newCard);

    }
}
