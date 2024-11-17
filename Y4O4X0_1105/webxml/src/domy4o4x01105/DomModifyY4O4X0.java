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

class DomModifyY4O4X0 {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("resources/hallgato.xml");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			// Get all "hallgato" elements
			NodeList hallgatok = doc.getElementsByTagName("hallgato");
			
			// Loop through all "hallgato" elements
			for (int i = 0; i < hallgatok.getLength(); i++) {
				Node hallgato = hallgatok.item(i);
				
				// Check if the node is an element
				if (hallgato.getNodeType() == Node.ELEMENT_NODE) {
					Element hallgatoElement = (Element) hallgato;
					
					// Get the "id" attribute
					String id = hallgatoElement.getAttribute("id");
					
					// If the id is "01", modify "keresztnev" and "vezeteknev"
					if ("01".equals(id)) {
						// Modify "keresztnev"
						Element keresztnevElement = (Element) hallgatoElement.getElementsByTagName("keresztnev").item(0);
						if (keresztnevElement != null) {
							keresztnevElement.setTextContent("Olivia");
						}
						
						// Modify "vezeteknev"
						Element vezeteknevElement = (Element) hallgatoElement.getElementsByTagName("vezeteknev").item(0);
						if (vezeteknevElement != null) {
							vezeteknevElement.setTextContent("Vigh");
						}
					}
				}
			}
			
			// Output the modified document
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			
			System.out.println("---Módosított fájl---");
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
