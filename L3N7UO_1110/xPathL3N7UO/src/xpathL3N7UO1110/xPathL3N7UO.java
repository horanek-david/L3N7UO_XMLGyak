package xpathL3N7UO1110;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class xPathL3N7UO {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException, XPathExpressionException {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse("studentL3N7UO.xml");
			
			document.getDocumentElement().normalize();
			
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			
			//1) Válassza ki az összes student element, amely a class gyermekei.
			String expression = "/class/student";
			
			
			
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				
				//4) Válassza ki a második student element, amely a class elem gyermeke.
				if(i==1) {
					
				//5) Válassza ki az utolsó student elemet, amely a class elem gyermeke.
				//(i== nodeList.getLength()-1) {
				
				//6) Válassza ki az utolsó elõtti student elemet, amely a class elem gyermeke
				//if(nodeList.getLength()>1 && i== nodeList.getLength()-2)
						
				//7) Válassza ki az elsõ két student elemet, amelyek a class elem gyermekei.
				//if(i<2) {
				
				Node node = nodeList.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					
					
					//2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "01".
					//if(element.getAttribute("id").equals("01")) {
					
					System.out.println("\nAktuális elem: " + node.getNodeName());
						 
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				
					}
				}
			} 
		}catch(ParserConfigurationException e) {
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}