package hu.domparse.y4o4x0;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadY4O4X0 {

    public static void main(String[] args) {
        try {
        	// Az XML fájl elérési útjának megadása
            File xmlFile = new File("src/resources/XMLY4O4X0.xml");
            
            // Az XML dokumentum felépítése (DOM objektum létrehozása)
            Document doc = buildDocument(xmlFile);
            
            // A gyökér elem kiírása
            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            
            // A "reszleg" elemek listájának lekérése
            NodeList reszlegList = doc.getDocumentElement().getElementsByTagName("reszleg");
            
            // Végighalad a "reszleg" elemek listáján
            for (int i = 0; i < reszlegList.getLength(); i++) {
                Node reszlegNode = reszlegList.item(i);
             	System.out.println("--------------------------------"); 
                System.out.println("\n\tJelenlegi elem: " + reszlegNode.getNodeName());

                //Tipus ellenőrzés
                if (reszlegNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element reszleg = (Element) reszlegNode;

                    // Részleg információinak kiírása
                    kiirReszlegInfo(reszleg);
                    
                    // "csapat" elemek lekérése és feldolgozása
                    NodeList csapatList = reszleg.getElementsByTagName("csapat");
                    for (int j = 0; j < csapatList.getLength(); j++) {
                        Node csapatNode = csapatList.item(j);
                        System.out.println("\tJelenlegi elem: " + csapatNode.getNodeName());

                        if (csapatNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element csapat = (Element) csapatNode;

                            // Csapat információinak kiírása
                            kiirCsapatInfo(csapat);

                            // "dolgozo" elemek lekérése és feldolgozása
                            NodeList dolgozoList = reszleg.getElementsByTagName("dolgozo");
                            for (int k = 0; k < dolgozoList.getLength(); k++) {
                                Node dolgozoNode = dolgozoList.item(k);
                                System.out.println("\tJelenlegi elem: " + dolgozoNode.getNodeName());

                                if (dolgozoNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element dolgozo = (Element) dolgozoNode;

                                    kiirDolgozoInfo(dolgozo);
                                }
                            }
                        }
                    }
                    
                    // "projekt" elemek lekérése és feldolgozása
                    NodeList projektLista = reszleg.getElementsByTagName("projekt");
                    for(int j = 0; j < projektLista.getLength(); j++) {
                    	 Node projektNode = projektLista.item(j);
                    	 System.out.println("\t jelenlegi elem: " + projektNode.getNodeName());
                    	 
                    	 if(projektNode.getNodeType() == Node.ELEMENT_NODE) {
                    		 Element projekt = (Element) projektNode;
                    		 
                             // Projekt információinak kiírása
                    		 kiirProjektInfo(projekt);
                    	 }
                    }
                    
                    // "ugyfel" elemek lekérése és feldolgozása
                    NodeList ugyfelList = reszleg.getElementsByTagName("ugyfel");
                    for(int k = 0 ; k < ugyfelList.getLength(); k++) {
                    	Node ugyfelNode = ugyfelList.item(0);
                    	System.out.println("\t Jelenlegi elem: " + ugyfelNode.getNodeName());
                    	
                    	if(ugyfelNode.getNodeType() == Node.ELEMENT_NODE) {
                    		Element ugyfel = (Element) ugyfelNode;
                    		
                            // Ügyfél információinak kiírása
                    		kiirUgyfelInfo(ugyfel);
                    	}
                    }
                    
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
  }
    
    // XML dokumentum felépítése (beolvasás és normalizálás)
	private static Document buildDocument(File xmlFile) throws Exception{
    	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    	Document doc = builder.parse(xmlFile);
    	
    	doc.getDocumentElement().normalize();
    	return doc;
    }

    // Részleg információinak kiírása
    private static void kiirReszlegInfo(Element reszleg) {
    	
        String rkod = reszleg.getAttribute("rkod");
        System.out.println("Rkód: " + rkod);

        Node nevNode = reszleg.getElementsByTagName("nev").item(0);
        String nev = nevNode.getTextContent();
        System.out.println("Név: " + nev);

        Node emeletNode = reszleg.getElementsByTagName("emelet").item(0);
        String emelet = emeletNode.getTextContent();
        System.out.println("Emelet: " + emelet);

        Node letszamNode = reszleg.getElementsByTagName("letszam").item(0);
        String letszam = letszamNode.getTextContent();
        System.out.println("Létszám: " + letszam);
        
    }

    // Csapat információinak kiírása
    private static void kiirCsapatInfo(Element csapat) {
        String cskod = csapat.getAttribute("cskod");
        String rkod = csapat.getAttribute("rkod");
        System.out.println("Cskód: " + cskod);
        System.out.println("Rkód: " + rkod);

        Node vezetoNode = csapat.getElementsByTagName("vezeto").item(0);
        String vezeto = vezetoNode.getTextContent();
        System.out.println("Vezető: " + vezeto);

        Node elorehaladasNode = csapat.getElementsByTagName("elorehaladas").item(0);
        String elorehaladas = elorehaladasNode.getTextContent();
        System.out.println("Előrehaladás: " + elorehaladas + "%");
    }

    // Dolgozó információinak kiírása
    private static void kiirDolgozoInfo(Element dolgozo) {
        String dkod = dolgozo.getAttribute("dkod");
        System.out.println("Dkód: " + dkod);

        Node fizetesNode = dolgozo.getElementsByTagName("fizetes").item(0);
        String fizetes = fizetesNode.getTextContent();
        System.out.println("Fizetés: " + fizetes);

        Node vegzettsegNode = dolgozo.getElementsByTagName("vegzettseg").item(0);
        String vegzettseg = vegzettsegNode.getTextContent();
        System.out.println("Végzettség: " + vegzettseg);

        Node belepesiDatumNode = dolgozo.getElementsByTagName("belepesiDatum").item(0);
        String belepesiDatum = belepesiDatumNode.getTextContent();
        System.out.println("Belépésidátum: " + belepesiDatum);

        kiirBeosztasInfo(dolgozo);
    }

    // Dolgozó beosztás információinak kiírása
    private static void kiirBeosztasInfo(Element dolgozo) {
        Element beosztas = (Element) dolgozo.getElementsByTagName("beosztas").item(0);
        System.out.println("\tJelenlegi elem: " + beosztas.getNodeName());

        String bkod = beosztas.getAttribute("bkod");
        System.out.println("Beosztás kód: " + bkod);

        Node pozicioNode = beosztas.getElementsByTagName("pozicio").item(0);
        String pozicio = pozicioNode.getTextContent();
        System.out.println("Pozíció: " + pozicio);

        Element munkaido = (Element) beosztas.getElementsByTagName("munkaido").item(0);
        kiirMunkaidoInfo(munkaido);
    }

    // Munkaidő információinak kiírása
    private static void kiirMunkaidoInfo(Element munkaido) {
        Node tolNode = munkaido.getElementsByTagName("tol").item(0);
        String tol = tolNode.getTextContent();
        System.out.println("Munkaidő - Tól: " + tol);

        Node igNode = munkaido.getElementsByTagName("ig").item(0);
        String ig = igNode.getTextContent();
        System.out.println("Munkaidő - Ig: " + ig);
    }
    
    // Projekt információinak kiírása
    private static void kiirProjektInfo(Element projekt) {
    	
    	System.out.println("Projekt kód: " + projekt.getAttribute("pkod"));
    	System.out.println("Részleg kód: " + projekt.getAttribute("rkod"));
    	System.out.println("Ügyfél kód: " + projekt.getAttribute("ukod"));
    	
    	Node nevNode = projekt.getElementsByTagName("nev").item(0);
    	String nev = nevNode.getTextContent();
    	System.out.println("Csapatnév: " + nev);
    	
    	Node felelosNode = projekt.getElementsByTagName("felelos").item(0);
    	String felelos = felelosNode.getTextContent();
    	System.out.println("Csapat felelős: " + felelos);
    	
    	Node hataridoNode = projekt.getElementsByTagName("hatarido").item(0);
    	String hatarido = hataridoNode.getTextContent();
    	System.out.println("Projekt határideje: " + hatarido);
    }

    // Ügyfél információinak kiírása
    private static void kiirUgyfelInfo(Element ugyfel) {
		System.out.println("Ügyfél kód: " + ugyfel.getAttribute("ukod"));
		System.out.println("Részleg kód: " + ugyfel.getAttribute("rkod"));
		
		Node nevNode = ugyfel.getElementsByTagName("nev").item(0);
		String nev = nevNode.getTextContent();
		System.out.println("Név: " + nev);
		
		Node adoszamNode = ugyfel.getElementsByTagName("adoszam").item(0);
		String adoszam = adoszamNode.getTextContent();
		System.out.println("Adószám: " + adoszam);
		
		Node cimNode = ugyfel.getElementsByTagName("cim").item(0);
		Element cim = (Element) cimNode;
		System.out.println("\tJelenlegi elem: " + cim.getNodeName());
		kiirUgyfelCimInfo(cim);
		
		Node emailNode = ugyfel.getElementsByTagName("email").item(0);
		String email = emailNode.getTextContent();
		System.out.println("Email: " + email);
	}

    //Ügyfél címének kiírása
	private static void kiirUgyfelCimInfo(Element cim) {
		Node varosNode = cim.getElementsByTagName("varos").item(0);
		String varos = varosNode.getTextContent();
		System.out.println("Város: " + varos);
		
		Node utcaNode = cim.getElementsByTagName("utca").item(0);
		String utca = utcaNode.getTextContent();
		System.out.println("Utca: " + utca);
		
		Node hazszamNode = cim.getElementsByTagName("hazszam").item(0);
		String hazszam = hazszamNode.getTextContent();
		System.out.println("Házszám: " + hazszam);
		
	}
}
