package com.api.valex.Services;

import com.api.valex.Controllers.dto.PaymentCardDto;
import com.api.valex.Controllers.dto.VirtualCardCreateDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Models.Businesses;
import com.api.valex.Models.Cards;
import com.api.valex.Models.Payments;
import com.api.valex.Models.Recharges;
import com.api.valex.Repositories.BusinessesRepository;
import com.api.valex.Repositories.CardRepository;
import com.api.valex.Repositories.PaymentRepository;
import com.api.valex.Repositories.RechargesRepository;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.api.valex.Middlewares.Crypt.crypt;

@Service
public class VirtualCardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusinessesRepository businessesRepository;

    @Autowired
    RechargesRepository rechargesRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public void CreateVirtual(VirtualCardCreateDto req) throws ErrorHandler400, ErrorHandler404 {
        Cards card = cardRepository.findBySecurityCode(req.getSecurityCode());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }

        if(card.getPassword() == null){
            throw new ErrorHandler400("400", "Cartão não ativado");
        }
        if(card.getBlocked()){
            throw new ErrorHandler400("400", "Cartão Bloqueado");
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
        virtualCard.setCard(card);
        cardRepository.save(virtualCard);
    }

    public void DeleteVirtual(VirtualCardCreateDto req) throws ErrorHandler404, ErrorHandler400 {
        Cards card = cardRepository.findBySecurityCode(req.getSecurityCode());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }
        if(!card.getVirtual()){
            throw new ErrorHandler400("400", "Cartão não é virtual");
        }
        String cryptPass = crypt(req.getPassword());
        if(!card.getPassword().equals(cryptPass)){
            throw new ErrorHandler400("400", "Senha incorreta");
        }

        cardRepository.delete(card);

    }

    public void VirtualTransaction(PaymentCardDto req) throws ErrorHandler400, ErrorHandler404 {
        if(req.getAmount() <= 0){
            throw new ErrorHandler400("400", "Valor da compra deve ser maior do que zero");
        }
        Cards card = cardRepository.findById(req.getCardId());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }

        LocalDate todayDate = LocalDate.now();
        if (todayDate.isAfter(card.getExpirationDate())) {
            throw new ErrorHandler400("400", "Cartão expirado");
        }

        if(card.getBlocked()){
            throw new ErrorHandler400("400", "Cartão bloqueado");
        }

        String cryptPass = crypt(req.getPassword());
        if(!card.getPassword().equals(cryptPass)){
            throw new ErrorHandler400("400", "Senha incorreta");
        }

        Businesses business = businessesRepository.findById(req.getBusinessId());
        if(business == null){
            throw new ErrorHandler400("400", "Estabelecimento não cadastrado");
        }

        if(card.getType() != business.getType()){
            throw new ErrorHandler400("400", "Tipo de transação incompatível");
        }

        List<Recharges> recharges = rechargesRepository.findAllByCardId(card.getCard().getId());
        List<Payments> payments = paymentRepository.findAllByCardId(card.getCard().getId());
        Float totalRecharge = 0.0F;
        for(Recharges r : recharges){
            totalRecharge += r.getAmount();
        }
        Float totalPayments = 0.0F;
        for(Payments p : payments){
            totalPayments += p.getAmount();
        }
        Float balance = totalRecharge - totalPayments - req.getAmount();
        Payments payment = new Payments();
        payment.setCard(card.getCard());
        payment.setAmount(req.getAmount());
        payment.setBusiness(business);
        payment.setTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        if(balance>0){
            paymentRepository.save(payment);
        } else {
            throw new ErrorHandler400("400", "Não há fundos suficientes para completar a transação");
        }

    }
}
