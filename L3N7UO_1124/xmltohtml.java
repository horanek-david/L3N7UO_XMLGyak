package projekt_2package;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class xmltohtml {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		transform("autokL3N7UO.xml", "autok7L3N7UO.xsl", "autok7L3N7UO.html");
	}

	public static void transform(final String xml, final String xslt, final String html) throws SAXException, IOException,
			ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		StreamSource xslCode = new StreamSource(new File(xslt));
		StreamSource xmlCode = new StreamSource(new File(xml));
		StreamResult outHtml = new StreamResult(new File(html));
		
		TransformerFactory tfFact = TransformerFactory.newInstance();
		Transformer transf = tfFact.newTransformer(xslCode);
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		transf.transform(xmlCode, outHtml);
		
	}

}
