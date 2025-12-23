package org.example.trainingspringboot.service;

import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
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

    public Image updateImage(int id , MultipartFile file) {
        try{
            Image image = imageRepository.getImageById(id);
            String originFolder = System.getProperty("user.dir") + image.getUrl();
            File fileSup = new File(originFolder);
            if (fileSup.exists()) {
                boolean delete = fileSup.delete();
                if (delete) {
                    System.out.println("delete success");

                }else {
                    System.out.println("delete fail");
                }
            }else {
                System.out.println("This file does not exist");
            }

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() +  ext;

            String imageFolder = "/images/";
            String uploads = System.getProperty("user.dir") + imageFolder;
            File folder = new File(uploads);
            File dest = new File(folder, fileName);
            file.transferTo(dest);

            image.setName(fileName);
            image.setUrl("/images/" + fileName);
            imageRepository.updateImage(image);
            return  image;

        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
        return null;
    }


    public Image showImageById(int id){
        return imageRepository.getImageById(id);
    }

    public List<Image> showAllImages(){
        return imageRepository.getAllImages();
    }

    public void deleteImageById(int id){
        Image image = imageRepository.getImageById(id);
        String originFolder = System.getProperty("user.dir") + image.getUrl();
        Path path = Paths.get(originFolder);
        try {
            Files.delete(path);
            System.out.println("delete file success");
        }catch (Exception e){
            System.out.println("delete fail");
        }
        imageRepository.deleteImageById(id);
    }

}

