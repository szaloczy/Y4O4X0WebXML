package SaxY4O4X01203;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxY4O4X0 {

    public static void main(String[] args) {
        try {
            //Dokumentum olvasó létrehozása, SAXParseFactor osztály newInstance metódus
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

            //Saját eseménykezelő létrehozása
            SaxHandler handler = new SaxHandler();

            //Dokumentum értelmezési folyamatának elindítása
            parser.parse(new File("src/resources/Y4O4X0_kurzusfelvetel.xml"), handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
