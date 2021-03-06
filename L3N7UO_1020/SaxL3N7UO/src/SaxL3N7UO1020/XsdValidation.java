package SaxL3N7UO1020;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import javax.xml.validation.Validator;
import java.io.*;

class XsdValidation {
  
  public static void validateXml(String schemaName, String xmlName) {
    try {
    	
      Schema schema = loadSchema(schemaName);
    	
      // creating a Validator instance
      Validator validator = schema.newValidator();

      // preparing the XML file as a SAX source
      SAXSource source = new SAXSource(
        new InputSource(new java.io.FileInputStream(xmlName)));

      // validating the SAX source against the schema
      validator.validate(source);
      System.out.println();
      System.out.println("XSD Validation successful");

    } catch (Exception e) {
      // catching all validation exceptions
      System.out.println();
      System.out.println(e.toString());
    }
  }
  
  public static Schema loadSchema(String name) {
    Schema schema = null;
    try {
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance(language);
      schema = factory.newSchema(new File(name));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return schema;
  }
  
}

