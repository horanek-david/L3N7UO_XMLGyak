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

public class XMLtoJSON4 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		//XML Fájl meghívása.
		File xmlFile = new File("XMLL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML Fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		
		NodeList bookList = doc.getElementsByTagName("element");
		for(int i=0; i<bookList.getLength(); i++) {
			Node nNode = bookList.item(i);
			Element elem = (Element) nNode;
			
			JSONObject obj2 = new JSONObject();
			
			Node nNode1 = elem.getElementsByTagName("author").item(0);
			obj2.put("author",nNode1.getTextContent());
			
			Node nNode2 = elem.getElementsByTagName("language").item(0);
			obj2.put("language",nNode2.getTextContent());
			
			list.add(obj2);
		}
		
		obj.put("element", list);
		
		System.out.print(obj);
		
		FileWriter file = null;
		try {
			 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("JSONL3N7UO.json");
            file.write(obj.toJSONString());
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
        	file.flush();
            file.close();
        }


	}
}
