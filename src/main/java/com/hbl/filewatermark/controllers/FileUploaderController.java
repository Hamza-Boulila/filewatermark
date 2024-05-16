package com.hbl.filewatermark.controllers;


import com.hbl.filewatermark.services.IAESEncryptionDecryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploaderController {
    @Autowired
    IAESEncryptionDecryption iaesEncryptionDecryption;
    String key = "HBL";
    String filesPath = "/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static";

    @GetMapping("/uploadfile")
    public String displayUploadForm() {
        return "fileuploader/index";
    }


    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(filesPath, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());

        byte[] encryptFile = iaesEncryptionDecryption.encrypt(file.getBytes(), key);
        Files.write(fileNameAndPath, encryptFile);
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        model.addAttribute("filePath", "/" + fileNames.toString());
        return "fileuploader/index";
    }
}
