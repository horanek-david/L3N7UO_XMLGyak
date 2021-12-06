package projekt_3package;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ObjectL3N7UO {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		//XML Fájl meghívása.
		File xmlFile = new File("JSONL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML Fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		JSONObject obj = new JSONObject();
		
		NodeList studentList = doc.getElementsByTagName("students");
		for(int i=0; i<studentList.getLength(); i++) {
			Node nNode = studentList.item(i);
			Element elem = (Element) nNode;
			
			Node nNode1 = elem.getElementsByTagName("nev").item(0);
			obj.put("nev",nNode1.getTextContent());
			
			Node nNode2 = elem.getElementsByTagName("fizetes").item(0);
			obj.put("fizetes",nNode2.getTextContent());
			
			Node nNode3 = elem.getElementsByTagName("kor").item(0);
			obj.put("kor",nNode3.getTextContent());
		}
		
		System.out.print(obj);

	}
}
