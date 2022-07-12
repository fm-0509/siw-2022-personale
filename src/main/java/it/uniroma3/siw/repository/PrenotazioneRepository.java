package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrenotazioneRepository extends CrudRepository <Prenotazione, Long> {


    List<Prenotazione> findByUtenteId(Long id);

    boolean existsPrenotazioneByUtenteAndStanza(User user, Stanza stanza);

    boolean existsPrenotazioneByStanza(Stanza stanza);
}
