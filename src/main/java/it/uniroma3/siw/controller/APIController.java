package it.uniroma3.siw.controller;

import it.uniroma3.siw.BootstrapAlert;
import it.uniroma3.siw.JSONResponse;
import it.uniroma3.siw.service.StanzaService;
import org.springframework.beans.factory.annotation.Autowired;
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
        this.stanzaService.removeStanza(id);
        return JSONResponse.CreateOKResponse("");
    }

    @RequestMapping(value = BASEPATH+"/stanzaForm/{id}")
    public JSONResponse modifyStanza(@ModelAttribute("id") Long id)
    {
        return JSONResponse.CreateOKResponse("<div class=\"modal\" tabindex=\"-1\" id=\"modal_form\">\n" +
                "  <div class=\"modal-dialog\">\n" +
                "    <div class=\"modal-content\">\n" +
                "      <div class=\"modal-header\">\n" +
                "        <h5 class=\"modal-title\">Modal title</h5>\n" +
                "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                "      </div>\n" +
                "      <div class=\"modal-body\">\n" +
                "        <p>Modal body text goes here.</p>\n" +
                "      </div>\n" +
                "      <div class=\"modal-footer\">\n" +
                "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\n" +
                "        <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n");
    }


    }