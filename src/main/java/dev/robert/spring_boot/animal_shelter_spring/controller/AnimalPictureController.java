package dev.robert.spring_boot.animal_shelter_spring.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.robert.spring_boot.animal_shelter_spring.controller.base.ControllerBase;
import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalPictureDTO;
import dev.robert.spring_boot.animal_shelter_spring.model.AnimalPicture;
import dev.robert.spring_boot.animal_shelter_spring.service.AnimalPictureService;

@RestController
@RequestMapping("api/v1/picture")
public class AnimalPictureController extends ControllerBase<
    AnimalPicture,
    AnimalPictureDTO,
    AnimalPictureDTO,
    Long
>{
    private final AnimalPictureService service;

    public AnimalPictureController(AnimalPictureService service) {
        this.service = service;
    }

    @Override
    protected AnimalPictureService getService() {
        return service;
    }

    @PostMapping("/upload")
    public ResponseEntity<AnimalPictureDTO> uploadPicture(
            @RequestParam("file") MultipartFile file,
            @RequestParam("animalId") Long animalId) throws IOException {
        AnimalPictureDTO dto = new AnimalPictureDTO();
        dto.setFilename(file.getOriginalFilename());
        dto.setSize(file.getSize());
        dto.setPicture(file.getBytes());
        dto.setAnimalId(animalId);
        AnimalPictureDTO saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPicture(@PathVariable Long id) {
        AnimalPictureDTO dto = service.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(dto.getPicture());
    }


}
