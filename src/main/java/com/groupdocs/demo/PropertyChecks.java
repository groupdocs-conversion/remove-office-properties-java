package com.groupdocs.demo;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.zip.*;

final class PropertyChecks {
    static void verify() throws Exception {
        String[] markers = {"Internal Review Team", "Quarterly Review Draft",
            "INTERNAL-REF-2610"};
        for (String type : new String[] {"docx", "xlsx", "pptx"}) {
            for (boolean clean : new boolean[] {false, true}) {
                String name = type + (clean ? "-clean." : "-retained.") + type;
                StringBuilder properties = new StringBuilder();
                try (ZipFile zip = new ZipFile(Paths.get("Result", name).toFile())) {
                    Enumeration<? extends ZipEntry> entries = zip.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        if (entry.getName().startsWith("docProps/")) {
                            properties.append(new String(zip.getInputStream(entry)
                                .readAllBytes(), StandardCharsets.UTF_8));
                        }
                    }
                }
                for (String marker : markers) {
                    if (properties.toString().contains(marker) == clean) {
                        throw new IllegalStateException("Property check failed: " + name);
                    }
                }
                System.out.println("PASS properties: " + name);
            }
        }
    }
}
