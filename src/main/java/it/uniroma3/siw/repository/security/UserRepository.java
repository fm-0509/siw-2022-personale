package it.uniroma3.siw.repository.security;

import it.uniroma3.siw.model.security.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

}