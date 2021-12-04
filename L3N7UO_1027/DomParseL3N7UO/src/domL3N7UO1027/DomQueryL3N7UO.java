package domL3N7UO1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryL3N7UO {
	public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {
		
		//XML Fájl meghívása.
		File xmlFile = new File("carsL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Kilistázás
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		System.out.println("----------------------------");
		
		NodeList carFerrariList = doc.getElementsByTagName("supercars");
		for(int i=0; i<carFerrariList.getLength(); i++) {
			Node nNode = carFerrariList.item(i);
			printCar(nNode, "Ferrari");
		}
		
		System.out.println("");
		
		NodeList carLamorginiList = doc.getElementsByTagName("supercars");
		for(int i=0; i<carLamorginiList.getLength(); i++) {
			Node nNode = carLamorginiList.item(i);
			printCar(nNode, "Lamborgini");
		}

	}
	
	private static void printCar(Node nNode, String search) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elemSuperCars = (Element) nNode;
			String scCompany = elemSuperCars.getAttribute("company");
			
			if(scCompany.equals(search)) {
				System.out.printf("supercarscompany: %s%n", scCompany);
				
				NodeList carNameList = elemSuperCars.getElementsByTagName("carname");
				for(int i=0; i<carNameList.getLength(); i++) {
					Element elemCarName = (Element) carNameList.item(i);
					String type = elemCarName.getAttribute("type");

					System.out.printf("car name: %s%n", elemCarName.getTextContent());
					System.out.printf("car type: %s%n", type);
				}
			}
			
			
		}
	}
}
