package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@RequestMapping("api/v1/animal")
public class AnimalController extends ControllerBase<
    Animal,
    AnimalRequestDTO,
    AnimalResponseDTO,
    Long
>{


    private final AnimalService animalService;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
    }

    public AnimalService getService() {
        return animalService;
    }


    @PatchMapping("admin/patch/{field}/{id}")
    public ResponseEntity<AnimalResponseDTO> patch(@PathVariable Long id, @PathVariable String field, @RequestBody AnimalRequestDTO req){
        AnimalResponseDTO response= animalService.patch(id, field, req);
        return ResponseEntity.ok(response);
    }

    @PutMapping("admin/upload/{id}")
    public ResponseEntity<AnimalResponseDTO> handleImageUpload(@PathVariable Long id, @RequestParam MultipartFile image, HttpServletResponse servletResponse) throws IOException {
        AnimalResponseDTO response = animalService.saveImage(id, image);
        return ResponseEntity.ok(response);
    }

    @GetMapping("public/download/{id}/{fileName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id, @PathVariable String fileName) throws IOException{
        byte[] response = animalService.getImage(id, fileName);

        // Infer content type based on file name (simple approach)
        MediaType mediaType;
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (fileName.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            mediaType = MediaType.IMAGE_GIF;
        } else {
            mediaType = MediaType.APPLICATION_OCTET_STREAM; // fallback
        }

        ByteArrayResource resource = new ByteArrayResource(response);
        return ResponseEntity.ok()
            .contentType(mediaType)
            .contentLength(resource.contentLength())
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    ContentDisposition.inline() // inline instead of attachment
                            .filename(fileName)
                            .build().toString())
            .body(resource);
    }


    @PatchMapping("admin/changePrimaryPic/{id}/{index}")
    public ResponseEntity<AnimalResponseDTO> changePrimaryPic(@PathVariable Long id, @PathVariable int index) throws FileNotFoundException{
        AnimalResponseDTO response = animalService.changeFirstImage(id, index);
        return ResponseEntity.ok(response);
    }

}
