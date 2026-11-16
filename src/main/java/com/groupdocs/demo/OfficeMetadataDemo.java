// Topic: Remove Office properties during conversion with GroupDocs.Conversion 26.10.
package com.groupdocs.demo;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.licensing.License;
import com.groupdocs.conversion.options.load.*;
import com.groupdocs.conversion.options.convert.*;
import java.nio.file.*;

public class OfficeMetadataDemo {
    public static void main(String[] args) throws Exception {
        applyLicense();
        Files.createDirectories(Paths.get("Result"));
        for (boolean clear : new boolean[] {false, true}) {
            convertWord(clear);
            convertExcel(clear);
            convertPowerPoint(clear);
        }
        PropertyChecks.verify();
        System.out.println("PASS: Office property retention and removal verified.");
    }

    private static void applyLicense() {
        String licensePath = "REPLACE_WITH_YOUR_LICENSE_PATH";
        if (Files.isRegularFile(Paths.get(licensePath))) {
            new License().setLicense(licensePath);
            System.out.println("License applied.");
        } else {
            System.out.println("Evaluation mode: output limits apply.");
        }
    }

    /**
     * Converts a DOCX report with source property cleanup enabled or disabled.
     *
     * @remarks Uses WordProcessingLoadOptions to control built-in and custom
     * document properties before conversion. The fictional report in documents/
     * contains a known author, title and InternalReference custom property.
     * Produces PDF and DOCX comparison files in Result/. Cleanup concerns
     * document properties, not visible text, comments or embedded file content.
     * The same-format output permits an independent OOXML property check.
     */
    public static void convertWord(boolean clear) {
        WordProcessingLoadOptions options = new WordProcessingLoadOptions();
        options.setClearBuiltInDocumentProperties(clear);
        options.setClearCustomDocumentProperties(clear);
        String target = "Result/docx-" + (clear ? "clean" : "retained");
        try (Converter converter = new Converter("documents/report.docx", () -> options)) {
            converter.convert(target + ".pdf", new PdfConvertOptions());
            converter.convert(target + ".docx", new WordProcessingConvertOptions());
        }
    }

    /**
     * Converts a XLSX report with source property cleanup enabled or disabled.
     *
     * @remarks Uses SpreadsheetLoadOptions to control built-in and custom
     * document properties before conversion. The fictional report in documents/
     * contains a known author, title and InternalReference custom property.
     * Produces PDF and XLSX comparison files in Result/. Cleanup concerns
     * document properties, not visible text, comments or embedded file content.
     * The same-format output permits an independent OOXML property check.
     */
    public static void convertExcel(boolean clear) {
        SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
        options.setClearBuiltInDocumentProperties(clear);
        options.setClearCustomDocumentProperties(clear);
        String target = "Result/xlsx-" + (clear ? "clean" : "retained");
        try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
            converter.convert(target + ".pdf", new PdfConvertOptions());
            converter.convert(target + ".xlsx", new SpreadsheetConvertOptions());
        }
    }

    /**
     * Converts a PPTX report with source property cleanup enabled or disabled.
     *
     * @remarks Uses PresentationLoadOptions to control built-in and custom
     * document properties before conversion. The fictional report in documents/
     * contains a known author, title and InternalReference custom property.
     * Produces PDF and PPTX comparison files in Result/. Cleanup concerns
     * document properties, not visible text, comments or embedded file content.
     * The same-format output permits an independent OOXML property check.
     */
    public static void convertPowerPoint(boolean clear) {
        PresentationLoadOptions options = new PresentationLoadOptions();
        options.setClearBuiltInDocumentProperties(clear);
        options.setClearCustomDocumentProperties(clear);
        String target = "Result/pptx-" + (clear ? "clean" : "retained");
        try (Converter converter = new Converter("documents/report.pptx", () -> options)) {
            converter.convert(target + ".pdf", new PdfConvertOptions());
            converter.convert(target + ".pptx", new PresentationConvertOptions());
        }
    }
}
