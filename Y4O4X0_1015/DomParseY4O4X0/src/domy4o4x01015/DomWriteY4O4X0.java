package domy4o4x01015;

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

public class DomWriteY4O4X0 {

    public static void main(String[] args) {
            String inputFilePath = "src/resources/Y4O4X0_orarend.xml";
            String outputFilePath = "src/resources/Y4O4X0_orarend1.xml";
            try {
                Document document = parseXmlFile(inputFilePath);
                writeDocumentToFile(document, outputFilePath);
                printXmlStructure(document.getDocumentElement(), " ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    private static void writeDocumentToFile(Document document, String filePath) throws Exception{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        DOMSource source = new DOMSource(document);
        StreamResult outFile = new StreamResult(new File(filePath));
        transformer.transform(source, outFile);
    }

    private static Document parseXmlFile(String filePath) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            return builder.parse(new File(filePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void printXmlStructure(Element element, String indent) {
        System.out.println("<" + element.getNodeName()+">");
            NodeList nodeList = element.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    printXmlStructure((Element) node, indent + "  ");
                } else if (node.getNodeType() == Node.TEXT_NODE) {
                    String content = node.getNodeValue().trim();
                      if (content.length() > 0) {
                          System.out.println(indent + content);
                      }
                }
            }
        System.out.println("</" + element.getNodeName()+">");
    }

}
