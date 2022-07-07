package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Stanza;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StanzaService {

    public Stanza getStanza()
    {
        Stanza s1 = new Stanza();
        s1.setNome("NomeTest");
        s1.setTipo("TipoTest");
        return s1;
    }

    public List<Stanza> getStanze()
    {
        Stanza s1 = new Stanza();
        s1.setNome("NomeTest");
        s1.setTipo("TipoTest");
        Stanza s2 = new Stanza();
        s2.setNome("NomeTest");
        s2.setTipo("TipoTest");
        Stanza s3 = new Stanza();
        s3.setNome("NomeTest");
        s3.setTipo("TipoTest");

        return Arrays.asList(s1, s2 , s3);

    }
}
