package com.hbl.filewatermark.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class PdfWatermarkService {

    public byte[] loadFile(String sourcePath) throws IOException
    {
        InputStream inputStream = new FileInputStream(sourcePath);
        byte[] result = inputStream.readAllBytes();
        inputStream.close();
        return result;
    }

    public void outputFile(byte[] bArray, String outputPath) throws IOException
    {
        OutputStream out = new FileOutputStream(outputPath);
        out.write(bArray);
        out.close();
    }




    public byte[] watermarkPdf(byte[] src, String watermark) throws IOException, DocumentException {
        Font blackFont = FontFactory.getFont("Arial", 26, BaseColor.LIGHT_GRAY);
        ByteArrayOutputStream res = new ByteArrayOutputStream();

        PdfReader reader = new PdfReader(new ByteArrayInputStream(src));
        PdfStamper stamper = new PdfStamper(reader, res);
        System.err.println(watermark.length());
        int pages = reader.getNumberOfPages();
        for (int i = 1; i <= pages; i++) {

            Rectangle pageRect = reader.getPageSize(i);
            float currentPosY = pageRect.getHeight();
            float currentPosX = pageRect.getWidth();

            while (currentPosY > 0) {
                while (currentPosX > 0) {
                    ColumnText.showTextAligned(stamper.getUnderContent(i),
                            Element.ALIGN_CENTER,
                            new Phrase( watermark, blackFont),
                            currentPosX, currentPosY, 45);
                    currentPosX -= 100;
                    //300
                }
                //300
                currentPosY -= 250;
                currentPosX = pageRect.getWidth();
            }
        }
        stamper.close();
        reader.close();

       return res.toByteArray();
    }

}


