package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("api/v1/animal")
public class AnimalController extends ControllerBase<
    Animal,
    AnimalRequestDTO,
    AnimalResponseDTO,
    Long
>{

    @Value("${app.images.dir:images}")
    private String imagesDir;

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
    }

    public AnimalService getService() {
        return animalService;
    }

    @PutMapping("upload/admin/{id}")
    public String handleImageUpload(@PathVariable String id, @RequestParam MultipartFile image, RedirectAttributes attr, HttpServletResponse response) throws IOException {
    // public String handleImageUpload(@PathVariable String id, RedirectAttributes attr, HttpServletResponse response) throws IOException{

        Path imagesPath = Paths.get(imagesDir); // from application.properties

        if (!Files.exists(imagesPath)) {                        //If images folder is not found
           Files.createDirectories(imagesPath);
        }

        String originalFilename = image.getOriginalFilename();
        String safeFileName = StringUtils.cleanPath(originalFilename != null ? originalFilename : "uploaded-file");
        Files.copy(image.getInputStream(), imagesPath.resolve(safeFileName), StandardCopyOption.REPLACE_EXISTING);


        attr.addAttribute("id", id); //TODO: Handle adding pictures to animal that does not yet exist
        return "redirect:/api/v1/animal/{id}";
    }


}
