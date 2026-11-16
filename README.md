# Remove Office Document Properties During PDF Conversion in Java

remove-office-properties-java is a runnable Java sample that compares Office document properties before and after conversion with cleanup enabled.

[![Docs](https://img.shields.io/badge/Docs-2865E0?style=for-the-badge)](https://docs.groupdocs.com/conversion/java/)
[![API Reference](https://img.shields.io/badge/API%20Reference-2865E0?style=for-the-badge)](https://reference.groupdocs.com/conversion/java/)
[![Blog](https://img.shields.io/badge/Blog-2865E0?style=for-the-badge)](https://blog.groupdocs.com/categories/groupdocs.conversion-product-family/)
[![Free Support](https://img.shields.io/badge/Free%20Support-2865E0?style=for-the-badge)](https://forum.groupdocs.com/c/conversion/)
[![Temporary License](https://img.shields.io/badge/Temporary%20License-2865E0?style=for-the-badge)](https://purchase.groupdocs.com/temp-license/100050)

## About This Repository

GroupDocs.Conversion for Java 26.10 supplies separate flags for built-in and
custom document properties. This project runs each Office conversion twice:
once retaining properties and once clearing them. A same-format output makes
the property changes independently inspectable in an OOXML ZIP package.

## The Challenge

A PDF export can retain an internal author or title from its Office source.
The sample inputs contain fictional values in three property fields. They
are deliberately separate from the visible report text. The demonstration
checks those properties without claiming that the report contains no
personal information elsewhere.

GroupDocs.Conversion is a document conversion SDK. Here it loads DOCX,
XLSX and PPTX with typed options and saves PDF plus an Office comparison copy.
PDF input cleanup is outside this project's tested scope.

## Prerequisites

- JDK 11 or 17 and Maven; verified locally with Java 11.0.2.
- GroupDocs.Conversion for Java 26.10, resolved by pom.xml.
- A license that covers this release for complete execution.

## Repository Structure

The project directory contains pom.xml, RUN.md, documents/ and
src/main/java/com/groupdocs/demo/. OfficeMetadataDemo.java contains the three
feature methods; PropertyChecks.java inspects output OOXML properties.
Result/ is created when the application runs and is excluded from version control.

## How do I run the comparison?

Open the project directory containing pom.xml with JDK 11 on your path.
Set the license placeholder locally if you need unrestricted execution,
then run the Maven command below. The program processes three fictional
Office files twice and checks the resulting properties. Keep your license
outside the project and revert its path before sharing source files.

```sh
mvn compile exec:java
```

## Code Examples

Each body below belongs to a method receiving `boolean clear`. The entry
point passes false and true in separate runs. The Result directory must exist.

### convertWord

```java
WordProcessingLoadOptions options = new WordProcessingLoadOptions();
options.setClearBuiltInDocumentProperties(clear);
options.setClearCustomDocumentProperties(clear);
String target = "Result/docx-" + (clear ? "clean" : "retained");
try (Converter converter = new Converter("documents/report.docx", () -> options)) {
    converter.convert(target + ".pdf", new PdfConvertOptions());
    converter.convert(target + ".docx", new WordProcessingConvertOptions());
}
```

### convertExcel

```java
SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
options.setClearBuiltInDocumentProperties(clear);
options.setClearCustomDocumentProperties(clear);
String target = "Result/xlsx-" + (clear ? "clean" : "retained");
try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
    converter.convert(target + ".pdf", new PdfConvertOptions());
    converter.convert(target + ".xlsx", new SpreadsheetConvertOptions());
}
```

### convertPowerPoint

```java
PresentationLoadOptions options = new PresentationLoadOptions();
options.setClearBuiltInDocumentProperties(clear);
options.setClearCustomDocumentProperties(clear);
String target = "Result/pptx-" + (clear ? "clean" : "retained");
try (Converter converter = new Converter("documents/report.pptx", () -> options)) {
    converter.convert(target + ".pdf", new PdfConvertOptions());
    converter.convert(target + ".pptx", new PresentationConvertOptions());
}
```

The application checks that Internal Review Team, Quarterly Review Draft
and INTERNAL-REF-2610 remain in retained Office copies and disappear from
clean Office copies. PDF Author and Title were checked separately with
Poppler pdfinfo. Generated PDF Producer information can remain.

## Related Topics to Explore

Before distributing a report, review visible text, comments and embedded
content separately. Property cleanup covers document properties rather
than every place where confidential information might occur.

## Related Resources

- [Scenario guide](https://docs.groupdocs.com/conversion/java/use-cases/remove-office-metadata/)
- [Companion article](https://blog.groupdocs.com/conversion/remove-office-metadata-java/)
- [GroupDocs.Conversion Java documentation](https://docs.groupdocs.com/conversion/java/)
- [Java API reference](https://reference.groupdocs.com/conversion/java/)

The scenario guide and article links identify companion drafts. They become
available after publication and the corresponding site deployments.

## Keywords

`remove document metadata Java`, `Office to PDF Java`, `clear custom document properties`, `DOCX metadata removal Java`, `XLSX metadata removal Java`, `PowerPoint metadata removal Java`, `GroupDocs.Conversion Java`, `Java 26.10`, `document author removal`, `document title removal`, `WordProcessingLoadOptions`, `SpreadsheetLoadOptions`, `PresentationLoadOptions`, `PDF document properties`, `Java Maven sample`
