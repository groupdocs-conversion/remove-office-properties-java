# Run the Java 26.10 demonstration

Use JDK 11 or 17 and Maven. Run commands from this directory:

```sh
mvn compile exec:java
```

The dependency is GroupDocs.Conversion for Java 26.10. Output is written to
`Result/`. A fresh Converter is used for each input/options combination.

The source contains `REPLACE_WITH_YOUR_LICENSE_PATH`. Replace it locally
with your own license path to run without evaluation limits. Never commit
that local change or include a license file. An unlicensed run can fail on
larger outputs; it is not a complete verification of the demonstration.

Main class: `com.groupdocs.demo.OfficeMetadataDemo`.
