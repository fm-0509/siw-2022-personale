package it.uniroma3.siw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController  {

    @RequestMapping("/")
    public Object defResponse()
    {
        return null;
    }

}