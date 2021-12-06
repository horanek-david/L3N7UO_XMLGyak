package projekt_3package;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLtoJSON1 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		//XML Fájl meghívása.
		File xmlFile = new File("autokL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML Fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		
		NodeList autokList = doc.getElementsByTagName("auto");
		for(int i=0; i<autokList.getLength(); i++) {
			Node nNode = autokList.item(i);
			Element elem = (Element) nNode;
			
			JSONObject obj2 = new JSONObject();
			
			obj2.put("rsz", elem.getAttribute("rsz"));
			
			Node nNode1 = elem.getElementsByTagName("tipus").item(0);
			obj2.put("tipus",nNode1.getTextContent());
			
			Node nNode2 = elem.getElementsByTagName("ar").item(0);
			obj2.put("ar",nNode2.getTextContent());
			
			Node nNode3 = elem.getElementsByTagName("szin").item(0);
			obj2.put("szin",nNode3.getTextContent());
			
			Node tulaj = elem.getElementsByTagName("tulaj").item(0);
			
			Element elem2 = (Element) tulaj;
			
			Node nev = elem2.getElementsByTagName("nev").item(0);
			Node varos = elem2.getElementsByTagName("varos").item(0);
			
			JSONObject obj3 = new JSONObject();
			obj3.put("nev", nev.getTextContent());
			obj3.put("varos", varos.getTextContent());
			obj2.put("tulaj", obj3);
			
			list.add(obj2);
		}
		
		obj.put("auto", list);
		
		
		System.out.print(obj);
		
		FileWriter file = null;
		try {
			 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("autokL3N7UO.json");
            file.write(obj.toJSONString());
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
        	file.flush();
            file.close();
        }

	}
}
