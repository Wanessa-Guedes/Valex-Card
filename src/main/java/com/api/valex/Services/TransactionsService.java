package com.api.valex.Services;

import com.api.valex.Controllers.dto.PaymentCardDto;
import com.api.valex.Controllers.dto.ReechargeCardDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Models.*;
import com.api.valex.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.api.valex.Middlewares.Crypt.crypt;

@Service
public class TransactionsService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CompaniesRepository companiesRepository;

    @Autowired
    EmployeeRepsoitory employeeRepsoitory;

    @Autowired
    RechargesRepository rechargesRepository;

    @Autowired
    BusinessesRepository businessesRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public void RechargeCard(String xApiKey, ReechargeCardDto req) throws ErrorHandler404, ErrorHandler400 {
        if(req.getAmout() <= 0){
            throw new ErrorHandler400("400", "Valor da recarga precisa ser maior do que zero");
        }
        Companies company = companiesRepository.findByApiKey(xApiKey);
        if (company == null) {
            throw new ErrorHandler404("404", "Empresa não registrada");
        }
        Employees employee = employeeRepsoitory.findById(req.getEmployeeId());
        if (employee == null) {
            throw new ErrorHandler404("404", "Funcionário não registrado");
        }

        Cards card = cardRepository.findById(req.getCardId());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }

        if(card.getPassword() == null){
            throw new ErrorHandler400("400", "Cartão não ativado");
        }

        LocalDate todayDate = LocalDate.now();
        if (todayDate.isAfter(card.getExpirationDate())) {
            throw new ErrorHandler400("400", "Cartão expirado");
        }

        // Verificar se o empregado é da empresa e se o cartão é dele
        List<Integer> isEmployee = new ArrayList<>();
        company.getEmployees().forEach(employeeList -> {
            if(employeeList.getId() == req.getEmployeeId()){
                if(card.getEmployee().getId() == req.getEmployeeId()){
                    isEmployee.add((int) req.getEmployeeId());
                }
            }
        } );

        if(isEmployee.size() == 0){
            throw new ErrorHandler400("400", "Algo saiu errado");
        }

        Recharges recharges = new Recharges();

        recharges.setCard(card);
        recharges.setTimestamp(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        recharges.setAmount(req.getAmout());

        rechargesRepository.save(recharges);
    }

    public void PaymentCard(PaymentCardDto req) throws ErrorHandler400, ErrorHandler404 {
        if(req.getAmount() <= 0){
            throw new ErrorHandler400("400", "Valor da compra deve ser maior do que zero");
        }
        Cards card = cardRepository.findById(req.getCardId());
        if(card == null){
            throw new ErrorHandler404("404", "Cartão não encontrado");
        }

        if(card.getPassword() == null){
            throw new ErrorHandler400("400", "Cartão não ativado");
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

        Businesses  business = businessesRepository.findById(req.getBusinessId());
        if(business == null){
            throw new ErrorHandler400("400", "Estabelecimento não cadastrado");
        }

        if(card.getType() != business.getType()){
            throw new ErrorHandler400("400", "Tipo de transação incompatível");
        }

        List<Recharges> recharges = rechargesRepository.findAllByCardId(req.getCardId());
        List<Payments> payments = paymentRepository.findAllByCardId(req.getCardId());
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
        payment.setCard(card);
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
