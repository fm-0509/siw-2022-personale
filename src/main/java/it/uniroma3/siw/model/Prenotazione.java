package it.uniroma3.siw.model;

import it.uniroma3.siw.costants.TipoPrenotazione;
import it.uniroma3.siw.model.security.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Getter @Setter
@Entity
@SequenceGenerator(name = "PREN_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "PREN_SEQ")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PREN_SEQUENCE_GENERATOR")
    private Long Id;

    @OneToOne
    private Stanza stanza;

    @ManyToOne
    private User utente;

    @Column
    TipoPrenotazione tipo;

}
