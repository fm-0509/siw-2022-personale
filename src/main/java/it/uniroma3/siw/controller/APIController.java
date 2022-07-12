package it.uniroma3.siw.controller;

import it.uniroma3.siw.BootstrapAlert;
import it.uniroma3.siw.JSONResponse;
import it.uniroma3.siw.model.Stanza;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.StanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
public class APIController  {

    @Autowired
    private StanzaService stanzaService;

    @Autowired
    private PrenotazioneService prenotazioneService;


    private static final String BASEPATH = "/api";

    @RequestMapping(value = BASEPATH+"/", method = RequestMethod.GET)
    public Object defResponse()
    {
        return JSONResponse.CreateOKResponse("----TEST-OK----");
    }

    @RequestMapping(value = BASEPATH+"/testError")
    public JSONResponse testError()
    {
        return JSONResponse.CreateErrorResponse("---TEST-ERROR---");
    }

/*@RequestMapping(value = BASEPATH+"/stanza/{id}", method = RequestMethod.GET)
    public JSONResponse stanzaById(@ModelAttribute(name = "id") Long id)
    {
        List<Object> data = new ArrayList<>();
        data.add(this.stanzaService.getStanza());
        return JSONResponse.CreateOKResponse(data);

    }*/

    @RequestMapping(value = BASEPATH+"/stanza", method = RequestMethod.GET)
    public JSONResponse getStanze()
    {
        return JSONResponse.CreateOKResponse((this.stanzaService.getStanze()));
    }


    /*@RequestMapping(value = BASEPATH+"/register", method = RequestMethod.POST)
    public JSONResponse registraUtente()
    {
        return JSONResponse.CreateErrorResponse(BootstrapAlert.Danger("<strong>Errore creazione</strong>").getHTML());
    }*/

    @RequestMapping(value = BASEPATH+"/stanza/{id}" ,method = RequestMethod.DELETE)
    public JSONResponse deleteStanza(@ModelAttribute("id") Long id)
    {
        if(this.prenotazioneService.existsPrenotazioneByStanza(this.stanzaService.getStanzaById(id)))
            return JSONResponse.CreateErrorResponse(BootstrapAlert.Warning("<Strong>Errore</strong> Sono presenti prenotazioni in questa stanza, impossibile eliminarla").getHTML());
        this.stanzaService.removeStanza(id);
        return JSONResponse.CreateOKResponse("");
    }

    @RequestMapping(value = BASEPATH+"/stanza/{id}", method = RequestMethod.GET)
    public JSONResponse<Stanza> getStanza(@ModelAttribute("id") Long id)
    {
        List<Stanza> stanza = new ArrayList<>();
        stanza.add(stanzaService.getStanzaById(id));
        return JSONResponse.CreateOKResponse(stanza);
    }

    @RequestMapping(value = BASEPATH+"/stanza", method = RequestMethod.PUT)
    public JSONResponse modificaStanza(@ModelAttribute("stanza") Stanza stanza,
                                       BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return JSONResponse.CreateErrorResponse("stanza non esistente");
        if(!this.stanzaService.existsById(stanza.getId()))
            return JSONResponse.CreateErrorResponse("stanza non esistente");
        this.stanzaService.save(stanza);
        return JSONResponse.CreateOKResponse("");
    }




    }