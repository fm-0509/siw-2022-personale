package it.uniroma3.siw.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
@Data
@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nome;
        private String cognome;
        private String email;

}

