package hu.domparse.y4o4x0;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMWriteY4O4X0 {

    public static void main(String[] args) {
        try {
            createAndPrintXMLDocument();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createAndPrintXMLDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Létrehozzuk a gyökérelemet XML namespace-ekkel
        Element rootElement = document.createElement("vallalat");
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaY4O4X0.xsd");
        document.appendChild(rootElement);

        // Részlegek hozzáadása
        addDepartment(document, rootElement, "r1", "Elektronika", "2", "12");
        addDepartment(document, rootElement, "r2", "Marketing", "3", "10");
        addDepartment(document, rootElement, "r3", "HR", "2", "6");

        // XML kiírása a konzolra
        printDocumentToConsole(document);
    }

    private static void addDepartment(Document document, Element rootElement, String rkod, String nev, String emelet, String letszam) {
        Element department = document.createElement("reszleg");
        department.setAttribute("rkod", rkod);

        // Részleg alá elemek hozzáadása
        appendElement(document, department, "nev", nev);
        appendElement(document, department, "emelet", emelet);
        appendElement(document, department, "letszam", letszam);

        // Csapatok hozzáadása
        addTeam(document, department, "cs1", rkod, "Szabó Ernő", "8", "30");
        addTeam(document, department, "cs2", rkod, "Nagy István", "43", "75");
        addTeam(document, department, "cs3", rkod, "Kovács András", "15", "45");

        // Projektek hozzáadása
        addProject(document, department, "p1", rkod, "Fejlesztési projekt", "Szabó Ernő", "2024-02-02");
        addProject(document, department, "p2", rkod, "Karbantartási projekt", "Kovács András", "2024-05-15");
        addProject(document, department, "p3", rkod, "Tesztelési projekt", "Nagy István", "2024-03-30");

        // Ügyfelek hozzáadása
        addClient(document, department, "u1", rkod, "János és Társai Kft.", "12345678-1-12", "Miskolc", "Petőfi út", "22", "janostarsai@gmail.com");
        addClient(document, department, "u2", rkod, "Banános Joe kft", "12573438-1-12", "Kistarcs", "Arany János utca", "8", "bananajoe@gmail.com");
        addClient(document, department, "u3", rkod, "Szepesi Kft.", "22245678-1-12", "Eger", "Puskás öcsi út", "34", "Szepesi@gmail.com");

        rootElement.appendChild(department);
    }

    private static void addTeam(Document document, Element parentElement, String cskod, String rkod, String vezeto, String letszam, String elorehaladas) {
        Element team = document.createElement("csapat");
        team.setAttribute("cskod", cskod);
        team.setAttribute("rkod", rkod);

        appendElement(document, team, "vezeto", vezeto);
        appendElement(document, team, "letszam", letszam);
        appendElement(document, team, "elorehaladas", elorehaladas);

        // Dolgozók hozzáadása
        addEmployee(document, team, "d1", "3000000", "Bsc diploma", "2006-02-02", "10:00:00", "18:00:00", "Szoftverfejlesztő", "Szabó Nórbert");
        addEmployee(document, team, "d2", "200000", "Középfokú végzettség", "2010-01-21", "10:00:00", "18:00:00", "Tesztelő", "Illés János");
        addEmployee(document, team, "d3", "300000", "Bsc diploma", "2000-03-03", "10:00:00", "18:00:00", "Tesztelő", "Fekete Csaba");

        parentElement.appendChild(team);
    }

    private static void addEmployee(Document document, Element teamElement, String dskod, String fizetes, String vegzettseg, String szuletes, String munkaido_kezd, String munkaido_veg, String pozicio, String nev) {
        Element employee = document.createElement("dolgozo");
        employee.setAttribute("dkod", dskod);

        appendElement(document, employee, "fizetes", fizetes);
        appendElement(document, employee, "vegzettseg", vegzettseg);
        appendElement(document, employee, "belepesiDatum", szuletes);

        Element workTime = document.createElement("munkaido");
        appendElement(document, workTime, "tol", munkaido_kezd);
        appendElement(document, workTime, "ig", munkaido_veg);
        employee.appendChild(workTime);

        Element position = document.createElement("beosztas");
        position.setAttribute("bkod", "b1");  // Igazítsuk a tényleges logikához
        appendElement(document, position, "pozicio", pozicio);
        employee.appendChild(position);

        appendElement(document, employee, "nev", nev);

        teamElement.appendChild(employee);
    }

    private static void addProject(Document document, Element department, String pskod, String rkod, String nev, String vezetok, String datum) {
        Element project = document.createElement("projekt");
        project.setAttribute("pkod", pskod);
        project.setAttribute("rkod", rkod);

        appendElement(document, project, "nev", nev);
        appendElement(document, project, "felelos", vezetok);
        appendElement(document, project, "hatarido", datum);

        department.appendChild(project);
    }

    private static void addClient(Document document, Element department, String ucode, String rkod, String nev, String adoszam, String varos, String utca, String hazszam, String email) {
        Element client = document.createElement("ugyfel");
        client.setAttribute("ukod", ucode);
        client.setAttribute("rkod", rkod);

        appendElement(document, client, "nev", nev);
        appendElement(document, client, "adoszam", adoszam);

        // Létrehozzuk a "cim" elemet
        Element address = document.createElement("cim");
        appendElement(document, address, "varos", varos);
        appendElement(document, address, "utca", utca);
        appendElement(document, address, "hazszam", hazszam);
        client.appendChild(address);

        appendElement(document, client, "email", email);

        department.appendChild(client);
    }

    private static void appendElement(Document document, Element parentElement, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parentElement.appendChild(element);
    }

    private static void printDocumentToConsole(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
