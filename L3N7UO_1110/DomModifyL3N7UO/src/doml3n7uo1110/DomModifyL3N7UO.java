package doml3n7uo1110;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyL3N7UO {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		//XML Fájl meghívása.
		File xmlFile = new File("nyelvekL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		printConsole(doc);
		
		System.out.println("----------- Modified File -----------");
		
		NodeList nodeList1 = doc.getElementsByTagName("szerver_nyelvek");
		for(int i=0; i<nodeList1.getLength(); i++) {
			Node nNode = nodeList1.item(i);
			changeNyelv(nNode, "Java", "OOP");
		}
		
		NodeList nodeList2 = doc.getElementsByTagName("szerver_nyelvek");
		for(int i=0; i<nodeList2.getLength(); i++) {
			Node nNode = nodeList2.item(i);
			changeNyelv(nNode, "MongoDB", "Document-oriented DB");
		}
		
		
		printConsole(doc);
		
	}
	
	private static void changeNyelv(Node nNode, String type, String name) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elemSzNyelv = (Element) nNode;
			
			NodeList nyelvList = elemSzNyelv.getElementsByTagName("nyelv_neve");
			for(int i=0; i<nyelvList.getLength(); i++) {
				Node nodeNyelv = nyelvList.item(i);
				Element elemNyelv = (Element) nodeNyelv;
				String type1 = elemNyelv.getAttribute("tipus");
				
				if(type1.equals(type)) {
					elemNyelv.setTextContent(name);
				}
			}
		}
	}
	
	private static void printConsole(Document doc) throws TransformerException {
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transf = transformerfactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		StreamResult console = new StreamResult(System.out);
		
		transf.transform(source, console);
	}
}