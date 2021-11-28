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

public class DomReadL3N7UO {
	public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {
		
		
		//XML Fájl meghívása.
		File xmlFile = new File("XMLL3N7UO.xml");
		
		//Dokument builder.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//XML Fájl betöltése a dBuilderbe.
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		//jatekvezetok kilistázása.
		System.out.println("\n----------Jatekvezetok----------");
		
		NodeList jatekvezetoList = doc.getElementsByTagName("jatekvezeto");
		for(int i=0; i<jatekvezetoList.getLength(); i++) {
			Node nNode = jatekvezetoList.item(i);
			printJatekvezeto(nNode);
		}
		
		//csapatok kilistázása.
		System.out.println("\n----------Csapatok----------");
		
		NodeList csapatList = doc.getElementsByTagName("csapat");
		for(int i=0; i<csapatList.getLength(); i++) {
			Node nNode = csapatList.item(i);
			printCsapat(nNode);
		}
		
		//jatekosok kilistázása.
		System.out.println("\n----------Jatekosok----------");
		
		NodeList jatekosList = doc.getElementsByTagName("jatekos");
		for(int i=0; i<jatekosList.getLength(); i++) {
			Node nNode = jatekosList.item(i);
			printJatekos(nNode);
		}
		
		//donto kilistázása.
		System.out.println("\n----------Donto----------");
		
		NodeList dontoList = doc.getElementsByTagName("donto");
		for(int i=0; i<dontoList.getLength(); i++) {
			Node nNode = dontoList.item(i);
			String dkod = printDonto(nNode);
			
			
			NodeList resztveszList = doc.getElementsByTagName("resztvesz");
			for(int j=0; j<resztveszList.getLength(); j++) {
				Node nNode2 = resztveszList.item(j);
				printResztvesz(nNode2, dkod);
			}
		}
		
	}
	
	private static void printJatekvezeto(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String jkod = elem.getAttribute("jkod");
			
			Node nNode1 = elem.getElementsByTagName("nev").item(0);
			String nev = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("orszag").item(0);
			String orszag = nNode2.getTextContent();
			
			System.out.printf("JKOD: %s%n", jkod);
			System.out.printf("Nev: %s%n", nev);
			System.out.printf("Orszag: %s%n", orszag);
			System.out.println("");
			
		}
	}
	
	private static void printCsapat(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String cskod = elem.getAttribute("cskod");
			
			Node nNode1 = elem.getElementsByTagName("nev").item(0);
			String nev = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("orszag").item(0);
			String orszag = nNode2.getTextContent();
			
			System.out.printf("CSKOD: %s%n", cskod);
			System.out.printf("Nev: %s%n", nev);
			System.out.printf("Orszag: %s%n", orszag);
			System.out.println("");
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
	
	private static String printDonto(Node nNode) {
		String dkod = null;
		
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			dkod = elem.getAttribute("dkod");
			
			Node nNode1 = elem.getElementsByTagName("orszag").item(0);
			String orszag = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("stadion").item(0);
			String stadion = nNode2.getTextContent();
			
			System.out.printf("DKOD: %s%n", dkod);
			System.out.printf("Orszag: %s%n", orszag);
			System.out.printf("Stadion: %s%n", stadion);
		
		}
		
		return dkod;
	}
	
	private static void printResztvesz(Node nNode, String dkod) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String dkod2 = elem.getAttribute("dkod");
			
			if(dkod2.equals(dkod)) {
				Node nNode2 = elem.getElementsByTagName("nezoszam").item(0);
				String nezoszam = nNode2.getTextContent();
				
				System.out.printf("Nezoszam: %s%n", nezoszam);
				System.out.println("");
			}
		}
	}
}