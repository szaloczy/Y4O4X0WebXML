package hu.domparse.y4o4x0;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomReadY4O4X0 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/XMLY4O4X0.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc .getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            NodeList reszlegList = doc.getDocumentElement().getElementsByTagName("reszleg");

            for(int i = 0; i < reszlegList.getLength(); i++) {
                Node reszlegNode = reszlegList.item(i);
                System.out.println("\nJelenlegi elem: " + reszlegNode.getNodeName());

                if(reszlegNode.getNodeType() == Node.ELEMENT_NODE){
                    Element reszleg = (Element) reszlegNode;

                    System.out.println("Rkód: " + reszleg.getAttribute("rkod"));
                    System.out.println("Név: " + reszleg.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Emelet: " + reszleg.getElementsByTagName("emelet").item(0).getTextContent());
                    System.out.println("Létszám: " + reszleg.getElementsByTagName("letszam").item(0).getTextContent());

                    NodeList csapatList = reszleg.getElementsByTagName("csapat");
                    for(int j = 0; j < csapatList.getLength(); j++) {
                        Node csapatNode = csapatList.item(j);
                        System.out.println("\t jelenlegi elem: " + csapatNode.getNodeName());

                        if(csapatNode.getNodeType() == Node.ELEMENT_NODE){
                            Element csapat = (Element) csapatNode;

                            System.out.println("Cskód: " + csapat.getAttribute("cskod"));
                            System.out.println("Rkód: " + csapat.getAttribute("rkod"));
                            System.out.println("Vezető: " + csapat.getElementsByTagName("vezeto").item(0).getTextContent());
                            System.out.println("Előrehaladás: " + csapat.getElementsByTagName("elorehaladas").item(0).getTextContent()+ "%");

                            NodeList dolgozoList = reszleg.getElementsByTagName("dolgozo");
                            for(int k = 0; k < dolgozoList.getLength(); k++) {
                                Node dolgozoNode = dolgozoList.item(k);
                                System.out.println("\tJelenlegi elem: " + dolgozoNode.getNodeName());

                                if(dolgozoNode.getNodeType() == Node.ELEMENT_NODE){
                                    Element dolgozo = (Element) dolgozoNode;

                                    System.out.println("Dkód: " + dolgozo.getAttribute("dkod"));
                                    System.out.println("Fizetes: " + dolgozo.getElementsByTagName("fizetes").item(0).getTextContent());
                                    System.out.println("Végzettség: " + dolgozo.getElementsByTagName("vegzettseg").item(0).getTextContent());
                                    System.out.println("Belépésidátum: " + dolgozo.getElementsByTagName("belepesiDatum").item(0).getTextContent());

                                    System.out.println("\tJelenlegi elem: " + dolgozo.getElementsByTagName("beosztas").item(0).getNodeName());
                                    Element beosztas = (Element) dolgozo.getElementsByTagName("beosztas").item(0);
                                    System.out.println("Beosztás kód: " + beosztas.getAttribute("bkod"));
                                    System.out.println("Pozíció: " + beosztas.getElementsByTagName("pozicio").item(0).getTextContent());

                                    // Munkaidő
                                    Element munkaido = (Element) beosztas.getElementsByTagName("munkaido").item(0);
                                    System.out.println("Munkaidő - Tól: " + munkaido.getElementsByTagName("tol").item(0).getTextContent());
                                    System.out.println("Munkaidő - Ig: " + munkaido.getElementsByTagName("ig").item(0).getTextContent());

                                }
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
