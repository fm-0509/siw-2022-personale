package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    protected PrenotazioneRepository prenotazioneRepository;

    @Transactional
    public boolean alreadyExists (Prenotazione prenotazione){
        return prenotazioneRepository.existsById(prenotazione.getId());
    }

    @Transactional
    public List<Prenotazione> getPrenotazioni(){
        List<Prenotazione> prenotazioni= new ArrayList<>();
        for(Prenotazione p: prenotazioneRepository.findAll()){
            prenotazioni.add(p);
        }
        return prenotazioni;
    }

    public void save(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniById(Long id) {
        List<Prenotazione> prenotazioni= new ArrayList<>();
        for(Prenotazione p: prenotazioneRepository.findByUtenteId(id)){
            prenotazioni.add(p);
        }
        return prenotazioni;
    }
}
