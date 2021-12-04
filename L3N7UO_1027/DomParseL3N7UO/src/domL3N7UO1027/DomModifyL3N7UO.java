package domL3N7UO1027;

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
		File xmlFile = new File("carsL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		printConsole(doc);
		
		System.out.println("----------- Modified File -----------");
		
		NodeList carFerrariList = doc.getElementsByTagName("supercars");
		for(int i=0; i<carFerrariList.getLength(); i++) {
			Node nNode = carFerrariList.item(i);
			changeCar(nNode, "Ferrari", "Lamborghini");
		}
		
		Node nNode2 = doc.getElementsByTagName("luxurycars").item(0);
		
		nNode2.getParentNode().removeChild(nNode2);
		
		printConsole(doc);
		
	}
	
	private static void changeCar(Node nNode, String oName, String nName) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elemSuperCars = (Element) nNode;
			String scCompany = elemSuperCars.getAttribute("company");
			
			if(scCompany.equals(oName)) {
				elemSuperCars.setAttribute("company", nName);
				
				NodeList carNameList = elemSuperCars.getElementsByTagName("carname");
				for(int i=0; i<carNameList.getLength(); i++) {
					
					Element elemCarName = (Element) carNameList.item(i);
					String oldName = elemCarName.getTextContent();
					elemCarName.setTextContent(oldName.replace(oName, nName));
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