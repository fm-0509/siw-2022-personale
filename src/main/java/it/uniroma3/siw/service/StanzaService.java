package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StanzaService {
  @Autowired
  protected StanzaRepository stanzaRepository;

  @Transactional
    public List<Stanza> getStanze(){
      List<Stanza> stanze= new ArrayList<>();
      for(Stanza s: stanzaRepository.findAll())
          stanze.add(s);
      return stanze;
  }

  @Transactional
  public void removeStanza(Long id){
    stanzaRepository.deleteById(id);
  }
  @Transactional
    public void save (Stanza stanza){
      stanzaRepository.save(stanza);
  }
}
