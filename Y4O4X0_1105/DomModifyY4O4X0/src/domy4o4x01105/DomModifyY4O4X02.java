package domy4o4x01105;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyY4O4X02 {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("resources/Y4O4X0_orarend.xml");
			File outputXmlFile = new File("resources/Y4O4X0_orarend1.xml");

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName("ora");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node oraNode = nodeList.item(i);
				
				if(oraNode.getNodeType() == Node.ELEMENT_NODE){
					Element ora = (Element) oraNode;
					String id = ora.getAttribute("id");
					
					
					if("O1".equals(id)) {
						Element oraado = doc.createElement("oraado");
					    oraado.setTextContent("Dr Baksa Attila");
					    ora.appendChild(oraado); 
					}
				}
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source  = new DOMSource(doc);
			
			System.out.println("Módosított fájl:");
			StreamResult console = new StreamResult(System.out);
			StreamResult output = new StreamResult(outputXmlFile);
			transformer.transform(source, console);
			transformer.transform(source, output);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
