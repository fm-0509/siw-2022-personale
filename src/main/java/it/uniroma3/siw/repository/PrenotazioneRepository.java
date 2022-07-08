package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Prenotazione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrenotazioneRepository extends CrudRepository <Prenotazione, Long> {


    List<Prenotazione> findByUtenteId(Long id);

}
