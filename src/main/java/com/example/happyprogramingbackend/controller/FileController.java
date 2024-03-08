package com.example.happyprogramingbackend.controller;

import com.example.happyprogramingbackend.Service.FilesStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {

    @Autowired
    FilesStorageService storageService;

    @GetMapping("")
    public ResponseEntity<InputStreamResource> download(
            @RequestParam(name = "nameFile", required = false) String nameFile
    ) throws IOException {
        System.out.println(nameFile);
        File file = new File("./src/data/" + nameFile);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            storageService.save(file);

            return file.getOriginalFilename();
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            return storageService.saveImage(file);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("")
    public Boolean delete(
            @RequestParam(name = "nameFile", required = true) String nameFile
    ) throws IOException {
        try {
            File file = new File("./src/data/" + nameFile);
            file.delete();

            return file.delete();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
