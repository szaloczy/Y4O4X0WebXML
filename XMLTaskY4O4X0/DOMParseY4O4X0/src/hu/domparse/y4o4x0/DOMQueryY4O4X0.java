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
            // Az XML fájl elérési útjának megadása
            File xmlFile = new File("src/resources/XMLY4O4X0.xml");
            // Az XML dokumentum felépítése (DOM objektum létrehozása)
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            queryForAllTeamAndLeader(doc);
            employeesWhoseSalaryOverAMillion(doc);
            projectDeadlinesBefore2024(doc);
            allCustomersNameAndEmail(doc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void allCustomersNameAndEmail(Document doc) {
        // 4. Az összes ügyfél neve és email címe
        System.out.println("\n4. Ügyfelek neve és email címe:");
        NodeList ugyfelek = doc.getElementsByTagName("ugyfel");
        for (int i = 0; i < ugyfelek.getLength(); i++) {
            Element ugyfel = (Element) ugyfelek.item(i);
            String nev = ugyfel.getElementsByTagName("nev").item(0).getTextContent();
            String email = ugyfel.getElementsByTagName("email").item(0).getTextContent();
            System.out.println("  - Név: " + nev + ", Email: " + email);
        }
    }

    //A csapat vezetők lekérdezése
    public static void queryForAllTeamAndLeader(Document doc) {
        System.out.println("1. Csapatok és vezetők");
        NodeList csapatok = doc.getElementsByTagName("csapat");
        for (int i = 0; i < csapatok.getLength(); i++) {
            Element csapat = (Element) csapatok.item(i);

            String csapatId = csapat.getAttribute("cskod");

            Node vezetoNode = csapat.getElementsByTagName("vezeto").item(0);
            String vezeto = vezetoNode.getTextContent();

            System.out.println(" - Csapat kód: " + csapatId + ", Vezető: " + vezeto);
        }
    }

    //Dolgozók lekérdezése kiknek fizetése 1 millió fölött van
    public static void employeesWhoseSalaryOverAMillion(Document doc) {
        System.out.println("\n2. Dolgozók 1 millió fizetés fölött");
        NodeList dolgozok = doc.getElementsByTagName("dolgozo");
        for(int i = 0; i < dolgozok.getLength(); i++) {
            Element dolgozo = (Element) dolgozok.item(i);

            String dkod = dolgozo.getAttribute("dkod");

            Node nevNode = dolgozo.getElementsByTagName("nev").item(0);
            String nev = nevNode.getTextContent();


            int fizetes = Integer.parseInt(dolgozo.getElementsByTagName("fizetes").item(0).getTextContent());
            if (fizetes > 1000000) {
                System.out.println(" - Dolgozó kód: " + dkod + ", Név:" + nev + ", Fizetes: " + fizetes);
            }
        }
    }

    public static void projectDeadlinesBefore2024(Document doc) {
        System.out.println("\n3. Projektek határideje 2024 május előtt:");
        NodeList projektek = doc.getElementsByTagName("projekt");
        for (int i = 0; i < projektek.getLength(); i++) {
            Element projekt = (Element) projektek.item(i);
            String hatarido = projekt.getElementsByTagName("hatarido").item(0).getTextContent();
            if (hatarido.compareTo("2024-05-01") < 0) {
                String nev = projekt.getElementsByTagName("nev").item(0).getTextContent();
                System.out.println("  - Projekt név: " + nev + ", Határidő: " + hatarido);
            }
        }
    }
}
