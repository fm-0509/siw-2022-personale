package it.uniroma3.siw.service;

import java.util.Optional;

import it.uniroma3.siw.model.security.Credential;
import it.uniroma3.siw.repository.security.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CredentialService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialRepository credentialsRepository;
	
	@Transactional
	public Credential getCredential(Long id) {
		Optional<Credential> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credential getCredential(String username) {
		Optional<Credential> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credential saveCredential(Credential credentials) {
        credentials.setRole(Credential.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

	@Transactional
    public Credential findByUsername(String username) {
		Optional<Credential> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
    }
}
