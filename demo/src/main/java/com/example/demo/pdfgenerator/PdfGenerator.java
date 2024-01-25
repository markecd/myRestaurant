package com.example.demo.pdfgenerator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import com.example.demo.models.Racun;

import java.io.IOException;

public class PdfGenerator {

    public static void generateRacunPdf(Racun racun, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Load the TrueType font
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                contentStream.showText("Racun Details:");
                moveTextPositionByAmount(contentStream, 0, -12); // Move to the next line
                contentStream.showText("ID: 3");
                moveTextPositionByAmount(contentStream, 0, -12); // Move to the next line
                contentStream.showText("Narocilo ID: 3");
                moveTextPositionByAmount(contentStream, 0, -12); // Move to the next line
                contentStream.showText("Natakar ID: 2");
                moveTextPositionByAmount(contentStream, 0, -12); // Move to the next line
                contentStream.showText("Koncen Znesek: 100e");

                contentStream.endText();
            }

            document.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void moveTextPositionByAmount(PDPageContentStream contentStream, float x, float y) throws IOException {
        contentStream.newLineAtOffset(x, y);
    }

    public static void main(String[] args) {
        // Test the PDF generation
        Racun sampleRacun = new Racun(); // Populate the Racun object with actual data
        generateRacunPdf(sampleRacun, "racun.pdf");
    }
}
