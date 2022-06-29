package it.uniroma3.siw.service.image;


import it.uniroma3.siw.model.image.Image;
import it.uniroma3.siw.repository.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Component
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public Image store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(filename, file.getContentType(), file.getBytes());
        return this.imageRepository.save(image);
    }

    public Image findById(String id)
    {
        return this.imageRepository.findById(id).orElse(null);
    }

}
