package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.model.Enterprise;
import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.service.IProfileService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/*@RestController
@RequestMapping("/profiles")*/
@Data
@Controller
public class ProfileController {
    @Autowired
    private IProfileService service;

   @Autowired
    @Qualifier("profileMapper")
    private ModelMapper mapper;

    @GetMapping("/profiles")
    public String viewHomePage(Model model){
        model.addAttribute("profiles",service.getAllProfile());
        return "profile/profiles";
    }
    @GetMapping("/profiles/nuevo")
    public String formularioRegistro(Model model){
        Profile profile = new Profile();
        model.addAttribute("profile",profile);
        return "profile/create_profile";
    }
    @PostMapping("/profiles")
    public String saveProfile(@ModelAttribute("profile")Profile profile){
        service.saveProfile(profile);
        return "redirect:/profiles";

    }
    @GetMapping("profiles/detalle/{id}")
    public ModelAndView detailProfile(@PathVariable("id") Integer id){
        if(!service.existSById(id))
            return new ModelAndView("redirect:/profile/profiles");
        Profile profile = service.getOne(id).get();
        ModelAndView mv = new ModelAndView("/profile/detail_profile");
        mv.addObject("profile", profile);
        return mv;
    }
    @GetMapping("/profiles/editar/{id}")
    public  String formularioEditar(@PathVariable(value = "id") Integer id,Model model){
        model.addAttribute("profile",service.getProfileById(id));
        return "profile/edit_profile";
    }
    @PostMapping("profiles/{id}")
    public String updateProfiles(@PathVariable Integer id, @ModelAttribute("profile") Profile profile, Model model){
        Profile profileExistent = service.getProfileById(id);
        profileExistent.setIdProfile(id);
        profileExistent.setImage(profile.getImage());
        profileExistent.setPhone(profile.getPhone());
        profileExistent.setCreatedAt(profile.getCreatedAt());

        service.updateProfile(profileExistent);
        return "redirect:/profiles";
    }
    @GetMapping("/profiles/{id}")
    public String deleteProfile(@PathVariable Integer id){
        service.deleteProfileById(id);
        return "redirect:/profiles";
    }

}

