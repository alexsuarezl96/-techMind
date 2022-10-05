package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.dto.EnterpriseDTO;
import com.techmind.project_enterprise.dto.ProfileDTO;
import com.techmind.project_enterprise.exeptions.ModelNotFoundException;
import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.service.IEnterpriseService;
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
    public ModelAndView detail_enterprise(@PathVariable("id") Long id,@AuthenticationPrincipal OidcUser principal){
        if(!service.existSById(id))
            return new ModelAndView("redirect:/enterprise/enterprises");
        Enterprise enterprise = service.getOne(id).get();
        ModelAndView mv = new ModelAndView("/enterprise/detail_enterprise");
        mv.addObject("enterprise", enterprise);
        mv.addObject("user", principal.getClaims());
        return mv;
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