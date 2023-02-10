package com.api.valex.Services;

import com.api.valex.Controllers.dto.ReechargeCardDto;
import com.api.valex.Middlewares.ErrorHandler400;
import com.api.valex.Middlewares.ErrorHandler404;
import com.api.valex.Models.Cards;
import com.api.valex.Models.Companies;
import com.api.valex.Models.Employees;
import com.api.valex.Models.Recharges;
import com.api.valex.Repositories.CardRepository;
import com.api.valex.Repositories.CompaniesRepository;
import com.api.valex.Repositories.EmployeeRepsoitory;
import com.api.valex.Repositories.RechargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void RechargeCard(String xApiKey, ReechargeCardDto req) throws ErrorHandler404, ErrorHandler400 {
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
}
