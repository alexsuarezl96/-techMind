package com.techmind.project_enterprise.controller;

import com.techmind.project_enterprise.model.User;
import com.techmind.project_enterprise.service.Impl.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class IndexController {

    UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
           User user = this.userService.getOrCreateUser(principal.getClaims());
        model.addAttribute("user", user);
            /*model.addAttribute("user", principal.getClaims());*/
        }
        return "index";
    }
    @GetMapping(value = {"/index"})
    public String index() {

        return "index";
    }

    @GetMapping(value = {"/dashboard"})
    public String dash(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("user", principal.getClaims());
        return "index/dashboard";
    }



   /* @GetMapping(value = {"/login"})
    public String login() {

        return "login/login";
    }*/

    @GetMapping("/forbidden")
    public String forbidden() {

        return "forbidden";
    }
}
