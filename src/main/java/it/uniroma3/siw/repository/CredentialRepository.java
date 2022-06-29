package it.uniroma3.siw.repository;

import java.util.Optional;

import it.uniroma3.siw.model.security.Credential;
import org.springframework.data.repository.CrudRepository;


public interface CredentialRepository extends CrudRepository<Credential, Long> {
	
	public Optional<Credential> findByUsername(String username);

}