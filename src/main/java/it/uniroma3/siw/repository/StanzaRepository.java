package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface StanzaRepository extends CrudRepository<Stanza, Long> {

}
