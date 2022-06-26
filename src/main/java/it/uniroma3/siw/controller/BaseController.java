package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// non è possibile chiamare un Controller 'Controller'
// perche altrimenti non riesce a gestire l'import dell'annotazione
public class BaseController  {
    @RequestMapping("/")
    public String defRequest()
    {
        return "index";
    }
}
