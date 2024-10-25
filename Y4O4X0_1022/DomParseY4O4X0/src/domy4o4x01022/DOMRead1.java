package domy4o4x01022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead1 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/Y4O4X0_orarend.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.parse(xmlFile);

            dom.getDocumentElement().normalize();
            NodeList oraList = dom.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Node oraNode = oraList.item(i);
                if (oraNode.getNodeType() == Node.ELEMENT_NODE){
                    printOraDetails((Element) oraNode);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printOraDetails(Element oraNode) {
        System.out.println("Ora ID: " + oraNode.getAttribute("id"));
        System.out.println("Ora Tipus: " +oraNode.getAttribute("tipus"));

        printTargy(oraNode);
        printIdopont(oraNode);
        printHelyszin(oraNode);
        printOktato(oraNode);
        printSzak(oraNode);
    }

    private static void printSzak(Element oraNode) {
        NodeList szakList = oraNode.getElementsByTagName("szak");
        for (int i = 0; i < szakList.getLength(); i++) {
            Node szakNode = szakList.item(i);
            if (szakNode.getNodeType() == Node.ELEMENT_NODE){
                System.out.println("Szak: " + szakNode.getTextContent());
            }
        }
    }

    private static void printOktato(Element oraNode) {
        NodeList oktatoList = oraNode.getElementsByTagName("oktato");
        for (int i = 0 ; i < oktatoList.getLength(); i++) {
            Node oktatoNode = oktatoList.item(i);
            if (oktatoNode.getNodeType() == Node.ELEMENT_NODE){
                System.out.println("Oktató: " + oktatoNode.getTextContent());
            }
        }
    }

    private static void printHelyszin(Element oraNode) {
        NodeList helyszinList = oraNode.getElementsByTagName("helyszin");
        for (int i = 0; i < helyszinList.getLength(); i++) {
            Node helyszinNode = helyszinList.item(i);
            if (helyszinNode.getNodeType() == Node.ELEMENT_NODE){
                System.out.println("Helyszín: " + helyszinNode.getTextContent());
            }
        }
    }

    private static void printIdopont(Element oraNode) {
        NodeList idopontList = oraNode.getElementsByTagName("idopont");
        for (int i = 0; i < idopontList.getLength(); i++) {
            System.out.print("Idopont: ");
            NodeList idopontChildList = idopontList.item(i).getChildNodes();
            for (int j = 0; j < idopontChildList.getLength(); j++) {
                Node idopontChildNode = idopontChildList.item(j);
                if (idopontChildNode.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(idopontChildNode.getTextContent() + "  ");
                }
            }
            System.out.println();
        }
    }

    private static void printTargy(Element oraNode) {
        NodeList targyList = oraNode.getElementsByTagName("targy");
        for (int i = 0; i < targyList.getLength(); i++) {
            Node targyNode = targyList.item(i);
            if (targyNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("targy: " + targyNode.getTextContent());
            }
        }
    }
}
