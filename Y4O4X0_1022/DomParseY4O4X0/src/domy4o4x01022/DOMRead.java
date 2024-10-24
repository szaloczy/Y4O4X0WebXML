package domy4o4x01022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/hallgatoY4O4X0.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            dom.getDocumentElement().normalize();
            NodeList hallgatoList= dom.getElementsByTagName("hallgato");

            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Node hallgatoNode = hallgatoList.item(i);
                if (hallgatoNode.getNodeType() == Node.ELEMENT_NODE) {
                    printHallgatoDetails((Element) hallgatoNode);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printHallgatoDetails(Element hallgatoNode) {
        System.out.println("Hallgato ID: " + hallgatoNode.getAttribute("id"));

        printKeresztnev(hallgatoNode);
        printVezeteknev(hallgatoNode);
        printFoglalkozas(hallgatoNode);
    }

    private static void printKeresztnev(Element hallgatoNode) {
        NodeList keresztnevList = hallgatoNode.getElementsByTagName("keresztnev");
        for (int i = 0; i < keresztnevList.getLength(); i++) {
            Node keresztnevNode = keresztnevList.item(i);
            if (keresztnevNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Keresztnev: " + keresztnevNode.getTextContent());
            }
        }
    }

    private static void printVezeteknev(Element hallgatoNode) {
        NodeList vezeteknevList = hallgatoNode.getElementsByTagName("vezeteknev");
        for (int i = 0; i < vezeteknevList.getLength(); i++) {
            Node vezeteknevNode = vezeteknevList.item(i);
            if(vezeteknevNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Vezeteknev: " + vezeteknevNode.getTextContent());
            }
        }
    }

    private static void printFoglalkozas(Element hallgatoNode) {
        NodeList foglalkozasList = hallgatoNode.getElementsByTagName("foglalkozas");
        for (int i = 0; i < foglalkozasList.getLength(); i++) {
            Node foglalkozasNode = foglalkozasList.item(i);
            if(foglalkozasNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("Foglalkozas: " + foglalkozasNode.getTextContent());
            }
        }
    }
}
