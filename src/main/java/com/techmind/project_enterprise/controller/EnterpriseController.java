package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.service.IEnterpriseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnterpriseController {
    @Autowired
    private IEnterpriseService service;
    @Autowired
    @Qualifier("enterpriseMapper")
    private ModelMapper mapper;

    @GetMapping("/enterprises")
    public String viewHomePage(Model model,@AuthenticationPrincipal OidcUser principal) {

        model.addAttribute("enterprises", service.getAllEnterprise());
        model.addAttribute("user", principal.getClaims());
        return "enterprise/enterprises";
    }
    @GetMapping("/enterprises/nuevo")
    public String formularioRegistro(Model model,@AuthenticationPrincipal OidcUser principal){
        Enterprise enterprise = new Enterprise();
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("user", principal.getClaims());
        return "enterprise/create_enterprise";
    }
    @PostMapping("/enterprises")
    public String saveEnterprises(@ModelAttribute("enterprise")Enterprise enterprise,Model model,@AuthenticationPrincipal OidcUser principal){
        service.saveEnterprise(enterprise);
        model.addAttribute("user", principal.getClaims());
        return "redirect:/enterprises";

    }
    @GetMapping("enterprises/detalle/{id}")
    public String detail_enterprise(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal){
        Enterprise enterprise = service.getOne(id).get();
        model.addAttribute("enterprise", enterprise);
        model.addAttribute("user", principal.getClaims());
        return "enterprise/detail_enterprise";
    }
    @GetMapping("/enterprises/editar/{id}")
    public  String formularioEditar(@PathVariable Long id,Model model,@AuthenticationPrincipal OidcUser principal){
        model.addAttribute("enterprise",service.getEnterpriseById(id));
        model.addAttribute("user", principal.getClaims());
        return "enterprise/edit_enterprise";
    }
    @PostMapping("enterprises/{id}")
    public String updateEnterprises(@PathVariable Long id, @ModelAttribute("enterprise") Enterprise enterprise, Model model){
        Enterprise enterpriseExistent = service.getEnterpriseById(id);
        enterpriseExistent.setIdEnterprise(id);
        enterpriseExistent.setName_enterprise(enterprise.getName_enterprise());
        enterpriseExistent.setNit_enterprise(enterprise.getNit_enterprise());
        enterpriseExistent.setAddress_enterprise(enterprise.getAddress_enterprise());
        enterpriseExistent.setPhone_enterprise(enterprise.getPhone_enterprise());
        enterpriseExistent.setCreatedAt(enterprise.getCreatedAt());

        service.updateEnterprise(enterpriseExistent);
        return "redirect:/enterprises";
    }
    @GetMapping("/enterprises/{id}")
    public String deleteEnterprises(@PathVariable Long id){
        service.deleteEnterpriseById(id);
        return "redirect:/enterprises";
    }
}