package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.security.User;
import it.uniroma3.siw.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PrenotazioneValidator implements Validator {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Prenotazione.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Prenotazione prenotazione = (Prenotazione) target;
        if(this.prenotazioneService.alreadyExists(prenotazione));
            errors.reject("buffet.duplicato");
    }
}
