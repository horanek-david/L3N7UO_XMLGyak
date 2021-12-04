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
			
			
			//1) V�lassza ki az �sszes student element, amely a class gyermekei.
			String expression = "/class/student";
			
			//2) V�lassza ki azt a student elemet, amely rendelkezik "id" attrib�tummal �s �rt�ke "01".
			//String expression = "//student[@id='01']";
			
			//3) Kiv�lasztja az �sszes student elemet, f�ggetlen�l att�l, hogy hol vannak a dokumentumban.
			//String expression = "//student";
			
			//4) V�lassza ki a m�sodik student element, amely a class elem gyermeke.
			//String expression = "//student[2]";
			
			//5) V�lassza ki az utols� student elemet, amely a class elem gyermeke.
			//String expression = "/class/student[last()]";
			
			//6) V�lassza ki az utols� el�tti student elemet, amely a class elem gyermeke
			//String expression = "/class/student[last()-1]";
			
			//7) V�lassza ki az els� k�t student elemet, amelyek a class elem gyermekei.
			//String expression = "/class/student[position()<3]";
			
			//8) V�lassza ki az �sszes student element, amely a class gyermekei.
			//String expression = "/class/*";
			
			//9) V�lassza ki az �sszes student elemet, amely rendelkezik legal�bb egy b�rmilyen attrib�tummal.
			//String expression = "//student[@*]";
			
			//10) V�lassza ki a dokumentum �sszes elem�t.
			//String expression = "//*";
			
			//11) V�lassza ki a class elem �sszes student elem�t, amelyn�l a kor>20.
			//String expression = "/class/student[kor>20]";
			
			
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				
				Node node = nodeList.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("\nAktu�lis elem: " + node.getNodeName());
						 
					System.out.println("Hallgat� ID: " + element.getAttribute("id"));
					System.out.println("Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becen�v: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				
					}
				}
			//} 
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