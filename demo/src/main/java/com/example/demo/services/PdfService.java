package com.example.demo.services;

import com.example.demo.models.Izdelek;
import com.example.demo.models.Natakar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class PdfService {

    public void generatePdf(Natakar natakar, Iterable<Object[]> productList, double koncenZnesek) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 700);

                contentStream.showText("Racun");
                contentStream.newLine();
                contentStream.showText("Natakar: " + natakar.getIme() + " " + natakar.getPriimek());
                contentStream.newLine();
                contentStream.showText("Datum in cas: "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                contentStream.newLine();

                for (Object[] produkt : productList) {
                    Izdelek izdelek = (Izdelek) produkt[0];
                    contentStream.showText(izdelek.getNaziv() + " - " + izdelek.getCena() + "€   "  + produkt[1] + "X");
                    contentStream.newLine();
                }

                contentStream.showText("Skupni znesek: " + koncenZnesek + "€");
                contentStream.endText();
            }

            document.save("racun.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}