package com.hbl.filewatermark.controllers;

import com.hbl.filewatermark.services.PdfWatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

@Controller

public class filePreviewController {
    @Autowired
    PdfWatermarkService pdfWatermarkService;

    @GetMapping("/file")
    public String displayUploadForm(Model model) throws IOException {
        byte[] file = pdfWatermarkService.loadFile("/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static/tets.pdf");
        String fileBase64 = Base64.getEncoder().encodeToString(file);

        model.addAttribute("file", encrypt(fileBase64));
        return "pdfviewer.html";
    }


    public static String encrypt(String text) {
        StringBuilder encrypted = new StringBuilder();
        int shift= 2;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            encrypted.append(c);
        }
        return encrypted.toString();
    }


}
