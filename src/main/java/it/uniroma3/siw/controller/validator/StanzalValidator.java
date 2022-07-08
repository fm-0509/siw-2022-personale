package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.security.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StanzalValidator implements Validator {
    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        Stanza stanza= (Stanza) o;
        String nome = stanza.getNome().trim();
        String tipo = stanza.getTipo().trim();
        String dimensione = stanza.getDimensione().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (tipo.isEmpty())
            errors.rejectValue("tipo", "required");
        else if (tipo.length() < MIN_NAME_LENGTH || tipo.length() > MAX_NAME_LENGTH)
            errors.rejectValue("tipo", "size");

        if (dimensione.isEmpty())
            errors.rejectValue("dimensione", "required");
        else if (dimensione.length() < MIN_NAME_LENGTH || dimensione.length() > MAX_NAME_LENGTH)
            errors.rejectValue("dimensione", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}
