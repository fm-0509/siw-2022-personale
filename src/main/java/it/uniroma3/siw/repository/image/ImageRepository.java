package it.uniroma3.siw.repository.image;

import it.uniroma3.siw.model.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
