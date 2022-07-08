package it.uniroma3.siw.controller;

import it.uniroma3.siw.BootstrapAlert;
import it.uniroma3.siw.controller.validator.StanzalValidator;
import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.service.StanzaService;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class StanzaController {

    @Autowired
    private StanzaService stanzaService;


    @Autowired
    private StanzalValidator stanzaValidator;

    @GetMapping("/admin/removeStanza/{stanzaId}")
    public String removeStanza(@PathVariable ("stanzaId") Long id, Model model, RedirectAttributes redirectAttributes){
        stanzaService.removeStanza(id);
        redirectAttributes.addFlashAttribute("stanze", stanzaService.getStanze());
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Stanza eliminata correttamente</strong>"));
        return "redirect:/stanze";
    }

    @RequestMapping(value = { "/admin/addStanza" }, method = RequestMethod.POST)
    public String addStanza(@Valid @ModelAttribute(value="nStanza")Stanza stanza, Model model,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes){
        this.stanzaValidator.validate(stanza, bindingResult);
        if(!bindingResult.hasErrors()){
            this.stanzaService.save(stanza);
            redirectAttributes.addFlashAttribute("nStanza", stanza);
            redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Stanza aggiunta correttamente</strong>"));
            return "redirect:/stanze";
        }
        else{
            redirectAttributes.addFlashAttribute("stanza", stanza);
            redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Danger("<strong>Erroe</strong> parametri non corretti"));
            return "redirect:/stanze";
        }
    }

    @GetMapping("/stanze")
    public String getStanze(Model model){
        model.addAttribute("stanze", stanzaService.getStanze());
        model.addAttribute("nStanza", new Stanza());
        return "stanze";
    }

}
