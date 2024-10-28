package domy4o4x01022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.concurrent.ExecutionException;

public class DOMWrite1 {

    public static void main(String[] args) {
        String inputFilePath = "src/resources/Y4O4X0_orarend1.xml";
        String outputFilePath = "src/resources/Y4O4X0_orarend1.xml";

        try {
            Document document = parseXml(inputFilePath);
            writeDocumentToFile(document, outputFilePath );
            printXmlStructure(document.getDocumentElement(), "  ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static Document parseXml(String path){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(path);
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
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printXmlStructure(Element element, String indent) {
        System.out.println("<" + element.getNodeName() + ">");
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
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
