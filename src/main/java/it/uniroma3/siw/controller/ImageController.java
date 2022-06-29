package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.image.Image;
import it.uniroma3.siw.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;


    @RequestMapping(method = RequestMethod.GET, path = "/images/{uid}")
    public ResponseEntity<byte[]> getImage(@ModelAttribute(name = "uid") String uid)
    {
        Image temp = this.imageService.findById(uid);
        if(temp != null)
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, temp.getType())
                    .body(temp.getData());
        else
            return ResponseEntity.notFound().build();

    }
}
