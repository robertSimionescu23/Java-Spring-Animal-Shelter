package dev.robert.spring_boot.animal_shelter_spring.exceptions;

public class FileExTensionNotSupported extends RuntimeException{
    public FileExTensionNotSupported(String message) {
        super(message);
    }
}
