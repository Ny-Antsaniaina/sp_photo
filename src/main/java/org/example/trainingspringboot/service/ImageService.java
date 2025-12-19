package org.example.trainingspringboot.service;

import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addImage(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();

            if(originalFilename == null || !originalFilename.contains(".")) {
                throw new RuntimeException("error originale filename");
            }
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() +  ext;
            String imageFolder = "/images/";
            String uploads = System.getProperty("user.dir") + imageFolder;
            File folder = new File(uploads);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File dest = new File(folder, fileName);
            file.transferTo(dest);

            Image image = new Image();
            image.setName(fileName);
            image.setUrl(imageFolder + fileName);

            imageRepository.createImage(image);
            return image;
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
            return null;
        }

    }

}
