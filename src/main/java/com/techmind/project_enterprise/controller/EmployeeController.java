package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.model.Employee;
import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Transaction;
import com.techmind.project_enterprise.repository.IEnterpriseRepository;
import com.techmind.project_enterprise.repository.ITransactionRepository;
import com.techmind.project_enterprise.service.IEmployeeService;
import com.techmind.project_enterprise.service.IEnterpriseService;
import com.techmind.project_enterprise.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    /*servicios*/
    @Autowired
    private IEmployeeService service;
   /* @Autowired
    private IProfileService serviceProfile;*/
    @Autowired
    private IEnterpriseService serviceEnterprise;
    @Autowired
    private ITransactionService serviceTransaction;

    /*repositorio*/
    /*Autowired
    private IProfileRepository repoprofile;*/
    @Autowired
    private IEnterpriseRepository repoenterprise;
    @Autowired
    private ITransactionRepository repotransaction;
    @Autowired
    @Qualifier("employeeMapper")
    private ModelMapper mapper;


    @GetMapping("/employees")
    public String viewHomePage(Model model,@AuthenticationPrincipal OidcUser principal){
        model.addAttribute("employees",service.getAllEmployees());
        model.addAttribute("user", principal.getClaims());
        return "employee/employees";
    }

    @GetMapping("/employees/nuevo")
    public String formularioRegistro(Model model,@AuthenticationPrincipal OidcUser principal){
        Employee employee = new Employee();
        /*List<Profile>listprofiles= repoprofile.findAll();*/
        List<Enterprise>listenterprise= repoenterprise.findAll();
        List<Transaction>listtransaction=repotransaction.findAll();
        model.addAttribute("employee", employee);
        /*model.addAttribute("listprofiles",listprofiles);*/
        model.addAttribute("listenterprise",listenterprise);
        model.addAttribute("listtransaction",listtransaction);
        model.addAttribute("user", principal.getClaims());
        return "employee/create_employee";
    }
    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee,Model model,@AuthenticationPrincipal OidcUser principal){
        service.saveEmployee(employee);
        model.addAttribute("user", principal.getClaims());
        return "redirect:/employees";

    }
    @GetMapping("employees/detalle/{id}")
    public String detailEmployee(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal OidcUser principal){
        Employee employee = service.getOne(id).get();
        model.addAttribute("employee", employee);
        model.addAttribute("user", principal.getClaims());
        return "employee/detail_employee";
    }
    @GetMapping("/employees/editar/{id}")
    public  String formularioEditar(@PathVariable(value = "id") Integer id,Model model,@AuthenticationPrincipal OidcUser principal){
        Employee employee = new Employee();
        List<Enterprise>listenterprise= repoenterprise.findAll();
        List<Transaction>listtransaction= repotransaction.findAll();

        model.addAttribute("employee",service.getEmployeeById(id));
       /* model.addAttribute("listprofile", serviceProfile.getProfileById(id));*/
        model.addAttribute("listenterprise",listenterprise);
        model.addAttribute("listtransaction",listtransaction);
        model.addAttribute("user", principal.getClaims());
        return "employee/edit_employee";
    }
    @PostMapping("employees/{id}")
    public String updateProfiles(@PathVariable Integer id, @ModelAttribute("employee") Employee employee, Model model){
        Employee employeeExistent = service.getEmployeeById(id);
        employeeExistent.setIdEmploye(id);
        employeeExistent.setName_employee(employee.getName_employee());
        employeeExistent.setEmail_employee(employee.getEmail_employee());
        /*employeeExistent.setIdProfile(employee.getIdProfile());*/
        employeeExistent.setRoleName(employee.getRoleName());
        employeeExistent.setEnterprise(employee.getEnterprise());
        employeeExistent.setTransaction(employee.getTransaction());
        employeeExistent.setCreatedAt(employee.getCreatedAt());

        service.updateEmployee(employeeExistent);
        return "redirect:/employees";
    }
    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        service.deleteEmployeeById(id);

        return "redirect:/employees";
    }

}
