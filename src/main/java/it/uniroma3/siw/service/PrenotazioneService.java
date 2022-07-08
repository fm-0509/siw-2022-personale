package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrenotazioneService {

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
}
