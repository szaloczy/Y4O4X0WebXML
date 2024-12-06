package SaxY4O4X01203;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdValidation {

    public static void main(String[] args) {
        String xmlFile = "src/resources/Y4O4X0_kurzusfelvetel.xml";
        String xsdFile = "src/resources/Y4O4X0_kurzusfelvetel.xsd";

        if (validateXMLSchema(xsdFile, xmlFile)) {
            System.out.println("XSD Validation successful");
        } else {
            System.out.println("XSD Validation failed");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (SAXException | IOException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}
