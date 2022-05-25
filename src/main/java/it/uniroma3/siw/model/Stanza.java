package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
public class Stanza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String dimensione;


}
