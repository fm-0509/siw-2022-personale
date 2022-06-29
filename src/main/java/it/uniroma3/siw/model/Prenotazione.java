package it.uniroma3.siw.model;

import it.uniroma3.siw.costants.TipoPrenotazione;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @OneToOne
    private Stanza stanza;

    @ManyToOne
    private Utente utente;

    @Column
    TipoPrenotazione tipo;

}
