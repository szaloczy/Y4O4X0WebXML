package domy4o4x01022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWrite {

    public static void main(String[] args) {
        String inputFilePath = "src/resources/hallgatoY4O4X0.xml";
        String outputFilePath = "src/resources/hallgato1Y4O4X0.xml";
        try {
            Document document = parseXmlFile(inputFilePath);
            writeDocumentToFile(document, outputFilePath);
            printXmlStructure(document.getDocumentElement(), " ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Document parseXmlFile(String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            return doc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void writeDocumentToFile(Document document, String path) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(document);
            StreamResult outputFile = new StreamResult(new File(path));
            transformer.transform(source, outputFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printXmlStructure(Element element, String indent) {
        System.out.println("<" + element.getNodeName() + ">");

        NodeList nodeList = element.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                printXmlStructure((Element) node, indent+ "  ");
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                String content = node.getTextContent().trim();
                if (content.length() > 0) {
                    System.out.println(indent + content);
                }
            }
        }
        System.out.println("</" + element.getNodeName() + ">");
    }
}
