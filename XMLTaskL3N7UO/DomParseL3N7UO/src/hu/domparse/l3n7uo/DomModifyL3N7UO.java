package hu.domparse.l3n7uo;

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
		File xmlFile = new File("XMLL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Módosítás elõtt:
		//Minden jatekosok kilistázása.
		System.out.println("\n----------Jatekosok----------");
		
		NodeList jatekosList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosList.getLength(); i++) {
			Node nNode = jatekosList.item(i);
			printJatekos(nNode);
		}
		
		//Módosítás után:
		//Minden jatekosok-hoz feltöltés majd kilistázása.
		
		Node jNode = doc.getElementsByTagName("jatekosok").item(0);
		Element jatekosok = (Element) jNode;
		
		Node addJatekos1 = createJatekos(doc, "J07", "Eden Hazard", "Belgium", "Tamado", "ABC010");
		jatekosok.appendChild(addJatekos1);
		
		Node addJatekos2 = createJatekos(doc, "J08", "Thomas Müller", "Németország", "Kozeppalyas", "CBA010");
		jatekosok.appendChild(addJatekos2);
		
		Node addJatekos3 = createJatekos(doc, "J09", "Luka Modric", "Horvátország", "Kozeppalyas", "ABC020");
		jatekosok.appendChild(addJatekos3);
		
		Node addJatekos4 = createJatekos(doc, "J10", "Leroy Sané", "Németország", "Tamado", "CBA020");
		jatekosok.appendChild(addJatekos4);
		
		System.out.println("\n----------Jatekosok----------");
		
		jatekosList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosList.getLength(); i++) {
			Node nNode = jatekosList.item(i);
			printJatekos(nNode);
		}
		
		saveAsDoc(doc, "XMLL3N7UO_Modify.xml");
	}
	
	private static void printJatekos(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String jkod = elem.getAttribute("jkod");
			
			Node nNode1 = elem.getElementsByTagName("nev").item(0);
			String nev = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("orszag").item(0);
			String orszag = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("pozicio").item(0);
			String pozicio = nNode3.getTextContent();
			
			Node nNode4 = elem.getElementsByTagName("jatekosigazolvany").item(0);
			String jatekosigazolvany = nNode4.getTextContent();
			
			System.out.printf("JKOD: %s%n", jkod);
			System.out.printf("Nev: %s%n", nev);
			System.out.printf("Orszag: %s%n", orszag);
			System.out.printf("Pozicio: %s%n", pozicio);
			System.out.printf("Jatekosigazolvany: %s%n", jatekosigazolvany);
			System.out.println("");
		}
	}
	
	private static void printJatekos(Node nNode, String pozicio) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String jkod = elem.getAttribute("jkod");
			
			Node nNode1 = elem.getElementsByTagName("nev").item(0);
			String nev = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("orszag").item(0);
			String orszag = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("pozicio").item(0);
			String pozicio2 = nNode3.getTextContent();
			
			Node nNode4 = elem.getElementsByTagName("jatekosigazolvany").item(0);
			String jatekosigazolvany = nNode4.getTextContent();
			
			if(pozicio2.equals(pozicio)) {
				System.out.printf("JKOD: %s%n", jkod);
				System.out.printf("Nev: %s%n", nev);
				System.out.printf("Orszag: %s%n", orszag);
				System.out.printf("Pozicio: %s%n", pozicio);
				System.out.printf("Jatekosigazolvany: %s%n", jatekosigazolvany);
				System.out.println("");
			}
		}
	}
	
	private static Node createElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	
	private static Node createJatekos(Document doc, String jkod, String nev, String orszag, String pozicio, String jatekosigazolvany) {
		Element jatekos = doc.createElement("jatekos");
		jatekos.setAttribute("jkod", jkod);
		jatekos.appendChild(createElement(doc, "nev", nev));
		jatekos.appendChild(createElement(doc, "orszag", orszag));
		jatekos.appendChild(createElement(doc, "pozicio", pozicio));
		jatekos.appendChild(createElement(doc, "jatekosigazolvany", jatekosigazolvany));
		
		return jatekos;
	}
	
	private static void saveAsDoc(Document doc, String filename) throws TransformerException {
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transf = transformerfactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File xmlFile = new File(filename);
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(xmlFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
}
