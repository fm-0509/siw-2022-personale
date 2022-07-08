package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private PrenotazioneValidator prenotazioneValidator;

    @GetMapping("/prenotazioni")
    public String getPrenotazioni(Model model){
        model.addAttribute("prenotazioni", prenotazioneService.getPrenotazioni());
        return "/prenotazioni";
    }
}
