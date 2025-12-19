package org.example.trainingspringboot.controller;
import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @PostMapping("/add")
    public Image saveImage(@RequestParam("file") MultipartFile file) {
        return imageService.addImage(file);
    }
}
