package domy4o4yx01015;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteY4O4X0 {
	 
	public static void main(String[] args) {
		
	}
	
	private static void writeDocumentToFile(Document doc, String path) throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(doc);
		StreamResult outFile = new StreamResult(new File(path));
		transformer.transform(source, outFile);
		
	}
}
