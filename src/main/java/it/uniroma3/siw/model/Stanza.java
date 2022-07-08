package it.uniroma3.siw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@SequenceGenerator(name = "STANZA_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "STANZA_SEQ")

public class Stanza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "STANZA_SEQUENCE_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String dimensione;


}
