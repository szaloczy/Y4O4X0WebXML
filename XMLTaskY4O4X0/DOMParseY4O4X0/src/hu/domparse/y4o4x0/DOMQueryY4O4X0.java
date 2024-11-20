package hu.domparse.y4o4x0;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMQueryY4O4X0 {

    public static void main(String[] args) {

        try {
            File xmlFile = new File("src/resources/XMLY4O4X0.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            queryForAllTeamAndLeader(doc);
            employeesWhoseSalaryOverAMillion(doc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public static void queryForAllTeamAndLeader(Document doc) {
        System.out.println("1. Csapatok és vezetők");
        NodeList csapatok = doc.getElementsByTagName("csapat");
        for (int i = 0; i < csapatok.getLength(); i++) {
            Element csapat = (Element) csapatok.item(i);

            String csapatId = csapat.getAttribute("id");

            Node vezetoNode = csapat.getElementsByTagName("vezeto").item(0);
            String vezeto = vezetoNode.getTextContent();

            System.out.println(" - Csapat kód: " + csapatId + ", Vezető: " + vezeto);
        }
    }

    public static void employeesWhoseSalaryOverAMillion(Document doc) {
        System.out.println("\n2. Dolgozók 1 millió fizetés fölött");
        NodeList dolgozok = doc.getElementsByTagName("dolgozo");
        for(int i = 0; i < dolgozok.getLength(); i++) {
            Element dolgozo = (Element) dolgozok.item(i);

            String dkod = dolgozo.getAttribute("dkod");

            Node nevNode = dolgozo.getElementsByTagName("nev").item(0); //NEV NINCS !!!
            String nev = nevNode.getTextContent();


            int fizetes = Integer.parseInt(dolgozo.getElementsByTagName("fizetes").item(0).getTextContent());
            if (fizetes > 1000000) {
                System.out.println(" - Dolgozó kód: " + dkod + ", Név:" + nev + ", Fizetes: " + fizetes);
            }
        }
    }
}
