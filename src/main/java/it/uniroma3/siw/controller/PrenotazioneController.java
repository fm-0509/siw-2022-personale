package it.uniroma3.siw.controller;

import it.uniroma3.siw.BootstrapAlert;
import it.uniroma3.siw.Utils;
import it.uniroma3.siw.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.costants.TipoPrenotazione;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.security.Credential;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.StanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private PrenotazioneValidator prenotazioneValidator;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private StanzaService stanzaService;

    @GetMapping("/prenotazioni")
    public String getPrenotazioni(Model model){
        if(Utils.IsAdmin())
            model.addAttribute("prenotazioni", prenotazioneService.getPrenotazioni());
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credential credential=credentialService.findByUsername(userDetails.getUsername());
            model.addAttribute("prenotazioni", prenotazioneService.getPrenotazioniById(credential.getUser().getId()));
        }
        return "/prenotazioni";
    }

    @RequestMapping(value = { "/admin/addPrenotazione/{idStanza}" }, method = RequestMethod.POST)
    public String addPrenotazione(@PathVariable (value="idStanza") Long idStanza, @Valid @ModelAttribute(value="tipo") TipoPrenotazione tipo, Model model,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Prenotazione prenotazione= new Prenotazione();
        prenotazione.setTipo(tipo);
        prenotazione.setStanza(stanzaService.findById(idStanza));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credential credential=credentialService.findByUsername(userDetails.getUsername());
        prenotazione.setUtente(credential.getUser());
        this.prenotazioneValidator.validate(prenotazione, bindingResult);
        if(!bindingResult.hasErrors()){
            this.prenotazioneService.save(prenotazione);
            redirectAttributes.addFlashAttribute("prenotazione", prenotazione);
            redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Prenotazione aggiunta correttamente</strong>"));
            return "redirect:/prenotazioni";
        }
        else{
            redirectAttributes.addFlashAttribute("prenotazione", prenotazione);
            redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Danger("<strong>Erroe</strong> parametri non corretti"));
            return "redirect:/stanze";
        }
    }
}
