package com.hbl.filewatermark;

import com.hbl.filewatermark.services.IAESEncryptionDecryption;
import com.hbl.filewatermark.services.impl.IAESEncryptionDecryptionImpl;
import com.hbl.filewatermark.services.PdfWatermarkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilewatermarkApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(FilewatermarkApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        PdfWatermarkService pdfWatermarkService = new PdfWatermarkService();
        IAESEncryptionDecryption iaesEncryptionDecryption = new IAESEncryptionDecryptionImpl();

       // byte[] hblsFile = iaesEncryptionDecryption.decrypt(pdfWatermarkService.loadFile("/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static/lpdwwf.pdf"), "HBL");
       // pdfWatermarkService.outputFile(hblsFile, "/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static/decrypted.pdf");

        byte[] file = pdfWatermarkService.loadFile("/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static/test.pdf");

        byte[] filewatermarked = pdfWatermarkService.watermarkPdf(file, "TEST | HAZMA@TEST.MA");

        pdfWatermarkService.outputFile(filewatermarked, "/Users/hbl/IdeaProjects/filewatermark/src/main/resources/static/watermarked.pdf");
    }
}
