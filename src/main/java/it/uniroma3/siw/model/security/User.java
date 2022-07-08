package it.uniroma3.siw.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
@Data
@NoArgsConstructor
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
        private Long id;
        private String nome;
        private String cognome;
        private String email;

}

