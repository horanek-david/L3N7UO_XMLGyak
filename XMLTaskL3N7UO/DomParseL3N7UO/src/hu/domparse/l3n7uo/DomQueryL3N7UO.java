package hu.domparse.l3n7uo;

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
		File xmlFile = new File("XMLL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Minden jatekosok kilistázása.
		System.out.println("\n----------Jatekosok----------");
		
		NodeList jatekosList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosList.getLength(); i++) {
			Node nNode = jatekosList.item(i);
			printJatekos(nNode);
		}
		
		//Szûrések.
		//Minden "tamado" jatekosok kilistázása.
		System.out.println("\n----------Jatekosok szures utan:----------\n");
		System.out.println("\n----------Tamado Jatekosok----------");
		
		NodeList jatekosTamadoList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosTamadoList.getLength(); i++) {
			Node nNode = jatekosTamadoList.item(i);
			printJatekos(nNode, "Tamado");
		}
		
		//Minden "kozeppalyas" jatekosok kilistázása.
		System.out.println("\n----------Kozeppalyas Jatekosok----------");
		
		NodeList jatekosKozeppalyasList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosKozeppalyasList.getLength(); i++) {
			Node nNode = jatekosKozeppalyasList.item(i);
			printJatekos(nNode, "Kozeppalyas");
		}
		
		//Minden "kapus" jatekosok kilistázása.
		System.out.println("\n----------Kapus Jatekosok----------");
		
		NodeList jatekosKapusList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosKapusList.getLength(); i++) {
			Node nNode = jatekosKapusList.item(i);
			printJatekos(nNode, "Kapus");
		}
				
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
}