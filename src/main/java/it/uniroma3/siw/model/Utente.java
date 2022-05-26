package it.uniroma3.siw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


// in questo modo vengono generati da Lombok i getter e setter in automatico
// di default come public
@Getter @Setter

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nome;

    @Column
    private String cognome;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

}