package org.example.trainingspringboot.controller;
import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/images")
    public List<Image> images(){
        return imageService.showAllImages();
    }

    @GetMapping("/image/{id}")
    public Image getImageById(@PathVariable int id){
        return imageService.showImageById(id);
    }

    @PostMapping("/add")
    public Image saveImage(@RequestParam("file") MultipartFile file) {
        return imageService.addImage(file);
    }

    @PutMapping("/update/{id}")
    public Image updateImage(@RequestParam("file") MultipartFile file ,@PathVariable int id) {
        return imageService.updateImage(id, file);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImageById(@PathVariable int id) {
        imageService.deleteImageById(id);
    }
}
