package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Stanza;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StanzaRepository extends CrudRepository<Stanza, Long> {

    public Optional<Stanza> findById (Long id);
}
