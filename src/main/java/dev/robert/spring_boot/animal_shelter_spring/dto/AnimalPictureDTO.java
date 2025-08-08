package dev.robert.spring_boot.animal_shelter_spring.dto;

import dev.robert.spring_boot.animal_shelter_spring.dto.base.DTOBase;

public class AnimalPictureDTO extends DTOBase<Long>{
    private String filename;
    private Long size;
    private byte[] picture;
    private Long animalId;

    public AnimalPictureDTO() {
    }

    public AnimalPictureDTO(String filename, Long size, byte[] picture, Long animalId) {
        this.filename = filename;
        this.size = size;
        this.picture = picture;
        this.animalId = animalId;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public byte[] getPicture() {
        return picture;
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    public Long getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }


}
