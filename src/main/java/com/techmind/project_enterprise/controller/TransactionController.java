package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.dto.TransactionDTO;
import com.techmind.project_enterprise.exeptions.ModelNotFoundException;
import com.techmind.project_enterprise.model.Employee;
import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.model.Transaction;
import com.techmind.project_enterprise.repository.IEnterpriseRepository;
import com.techmind.project_enterprise.service.IEnterpriseService;
import com.techmind.project_enterprise.service.ITransactionService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Controller
public class TransactionController {

    @Autowired
    private ITransactionService service;
    @Autowired
    private IEnterpriseService serviceEnterprise;
    @Autowired
    private IEnterpriseRepository repoenterprise;

    @Autowired
    @Qualifier("transactionMapper")
    private ModelMapper mapper;

    @GetMapping("/transactions")
    public String viewHomePage(Model model,@AuthenticationPrincipal OidcUser principal){
        model.addAttribute("transactions",service.getAllTransaction());
        model.addAttribute("user", principal.getClaims());
        return "transaction/transactions";
    }
    @GetMapping("/transactions/nuevo")
    public String formularioRegistro(Model model,@AuthenticationPrincipal OidcUser principal){
        Transaction transaction = new Transaction();
        List<Enterprise>listenterprise= repoenterprise.findAll();
        model.addAttribute("transaction",transaction);
        model.addAttribute("listenterprise",listenterprise);
        model.addAttribute("user", principal.getClaims());
        return "transaction/create_transaction";
    }
    @PostMapping("/transactions")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction,Model model,@AuthenticationPrincipal OidcUser principal){
        service.saveTransaction(transaction);
        model.addAttribute("user", principal.getClaims());
        return "redirect:/transactions";

    }
    @GetMapping("transactions/detalle/{id}")
    public ModelAndView detailTransaction(@PathVariable("id") Long id,@AuthenticationPrincipal OidcUser principal){
        if(!service.existSById(id))
            return new ModelAndView("redirect:/transaction/transactions");
        Transaction transaction = service.getOne(id).get();
        ModelAndView mv = new ModelAndView("/transaction/detail_transaction");
        mv.addObject("transaction", transaction);
        mv.addObject("user", principal.getClaims());
        return mv;
    }
    @GetMapping("/transactions/editar/{id}")
    public  String formularioEditar(@PathVariable(value = "id") Long id,Model model,@AuthenticationPrincipal OidcUser principal){
        Transaction transaction = new Transaction();
        List<Enterprise>listenterprise= repoenterprise.findAll();
        model.addAttribute("transaction",service.getTransactionById(id));
        model.addAttribute("listenterprise",listenterprise);
        model.addAttribute("user", principal.getClaims());
        return "transaction/edit_transaction";
    }
    @PostMapping("transactions/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute("transaction") Transaction transaction, Model model){
        Transaction transactionExistent = service.getTransactionById(id);
        transactionExistent.setIdTransaction(id);
        transactionExistent.setConcept_transaction(transaction.getConcept_transaction());
        transactionExistent.setAmount_transaction(transaction.getAmount_transaction());
        transactionExistent.setEnterprise(transaction.getEnterprise());
        transactionExistent.setCreatedAt(transaction.getCreatedAt());

        service.updateTransaction(transactionExistent);
        return "redirect:/transactions";
    }
    @GetMapping("/transactions/{id}")
    public String deleteTransaction(@PathVariable Long id){
        service.deleteTransactionById(id);

        return "redirect:/transactions";
    }


}
