package dev.robert.spring_boot.animal_shelter_spring.animal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.FileExTensionNotSupported;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

@Service
public class AnimalService extends ServiceBase<
    Animal,
    AnimalRequestDTO,
    AnimalResponseDTO,
    Long
>{

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

   @Value("${app.images.dir:images}")
    private String imagesDir;



    @Override
    protected JpaRepository<Animal, Long> getRepository(){
        return animalRepository;
    }

    @Override
    protected AnimalMapper getMapper(){
        return animalMapper;
    }

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository   = animalRepository;
        this.animalMapper       = animalMapper;
    }

    public String getFIleExtension(MultipartFile file){
        String filePath = file.getOriginalFilename();
        if(filePath == null)
            return null;
        int    extensionIndex = filePath.lastIndexOf(".");
        String extenstion = filePath.substring(extensionIndex);

        return extenstion;
    }

    public AnimalResponseDTO patch(Long id, String field, AnimalRequestDTO req){

        Animal existingAnimal = getRepository().findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Animal with id " + id + " cannot be found."));

        switch (field) {
            case "name" -> {
                if (req.getName() != null)
                    existingAnimal.setName(req.getName());
                else throw new ResourceNotFoundException("name field of request was empty.");
            }
            case "age" -> {
                if (req.getAge() != null)
                    existingAnimal.setAge(req.getAge());
                else throw new ResourceNotFoundException("age field of request was empty.");
            }
            case "species" -> {
                if (req.getSpecies() != null)
                    existingAnimal.setSpecies(req.getSpecies());
                else throw new ResourceNotFoundException("species field of request was empty.");
            }
            case "description" -> {
                if (req.getDescription() != null)
                    existingAnimal.setDescription(req.getDescription());
                else throw new ResourceNotFoundException("Description field of request was empty.");
            }
            case "pictureURLs" -> {
                if (req.getPictureURLs() != null)
                    existingAnimal.setPictureURLs(req.getPictureURLs());
                else throw new ResourceNotFoundException("Picture URLs field of request was empty.");
            }
            default -> throw new ResourceNotFoundException("Field to patch cannot be found.");
        }

        Animal response = getRepository().save(existingAnimal);

        return getMapper().toDTO(response);
    }

    public AnimalResponseDTO addNewUrl(Long id, String fileName){ //Think about creating custom DTO for urls only
        Animal animal = getRepository().findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animal with id " + id + " does not exist in the data base."));

        List<String> existingURLs = animal.getPictureURLs();

        if(existingURLs == null){
            existingURLs = new ArrayList<String>();
        }

        existingURLs.addLast(fileName);

        animal.setPictureURLs(existingURLs);

        Animal response = getRepository().save(animal);
        return getMapper().toDTO(response);
    }

    public AnimalResponseDTO saveImage(Long id, MultipartFile image) throws IOException{

        Path imagesPath = Paths.get(imagesDir); // from application.properties

        if (!Files.exists(imagesPath)) { //If images folder is not found
            Files.createDirectories(imagesPath);
        }

        UUID identifier = UUID.randomUUID();
        String fileExtension = getFIleExtension(image);

        if(fileExtension == null){
            throw new IOException("The file does not have an extension");
        }

        else if(!fileExtension.equals(".jpg" ) &&
                !fileExtension.equals(".jpeg") &&
                !fileExtension.equals(".png")  &&
                !fileExtension.equals(".bmp")
                ) throw new FileExTensionNotSupported("Extension " + fileExtension + " is not supported.");

        String filename = identifier.toString() + getFIleExtension(image);

        AnimalResponseDTO response = addNewUrl(id, filename); //Add the picture URL to the enetity. This will throw error if the id does not exist.

        Files.copy(image.getInputStream(), imagesPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return response;
    }

    public byte[] getImage(Long id, String fileName) throws IOException{
        Animal animal = getRepository().findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Animal with id " + id + " does not exist in data base."));

        List<String> urls = animal.getPictureURLs();
        if (!urls.contains(fileName))
            throw new FileNotFoundException("There is no image named " + fileName + " associated with animal id " + id);

        Path imagesPath = Paths.get(imagesDir); // from application.properties

        if (!Files.exists(imagesPath)) { //If images folder is not found
            Files.createDirectories(imagesPath);
        }

        Path filePath = Paths.get(imagesPath.toString(), fileName);

        if(!Files.exists(filePath))
            throw new FileNotFoundException("PictureURL does not correspond to file in image directory.");

        return Files.readAllBytes(filePath);
    }

    public AnimalResponseDTO changeFirstImage(Long id, int index) throws FileNotFoundException{
        Animal animal = getRepository().findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Animal with id " + id + " does not exist in data base."));

        List<String> urls = animal.getPictureURLs();
        if (urls.size() - 1 < index)
            throw new FileNotFoundException("There is no image at index " + index);

        String aux;

        //Switch the first index with the index specified
        aux = urls.get(0);
        urls.set(0, urls.get(index));
        urls.set(index, aux);

        animal.setPictureURLs(urls);

        Animal response = getRepository().save(animal);

        return getMapper().toDTO(response);
    }

}
