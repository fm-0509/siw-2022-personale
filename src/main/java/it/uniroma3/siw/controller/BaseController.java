package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.security.Credential;
import it.uniroma3.siw.model.security.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// non è possibile chiamare un Controller 'Controller'
// perche altrimenti non riesce a gestire l'import dell'annotazione
public class BaseController  {
    @RequestMapping("/")
    public String defRequest(Model model)
    {
        model.addAttribute("credential", new Credential());
        model.addAttribute("user", new User());
        return "index";
    }
}
