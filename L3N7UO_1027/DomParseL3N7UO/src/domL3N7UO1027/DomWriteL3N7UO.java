package domL3N7UO1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomWriteL3N7UO {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		
		// Betöltés
		File xmlFile = new File("usersL3N7UO.xml");
		
		DocumentBuilderFactory factoryLoad = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilderLoad = factoryLoad.newDocumentBuilder();
		
		Document docLoad = dBuilderLoad.parse(xmlFile);
		
		
		
		
		
		// Kiiras
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("domL3N7UO", "users");
		doc.appendChild(root);
		
		NodeList nList = docLoad.getElementsByTagName("user");
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
						
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				
				String uid = elem.getAttribute("id");
				
				Node node1 = elem.getElementsByTagName("firstname").item(0);
				String fname = node1.getTextContent();
			
				Node node2 = elem.getElementsByTagName("lastname").item(0);
				String lname = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("profession").item(0);
				String pname = node3.getTextContent();

				root.appendChild(createUser(doc, uid, fname, lname, pname));
			}
		}
		
		
		//root.appendChild(createUser(doc, "1", "Pal", "Kiss", "programmer"));
		//root.appendChild(createUser(doc, "2", "Piroska", "Zold", "writer"));
		//root.appendChild(createUser(doc, "3", "Alma", "Gordon", "teacher"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("users1L3N7UO.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "lastname", lastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
}