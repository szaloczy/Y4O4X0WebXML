package hu.domparse.y4o4x0;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyY4O4X0 {

	public static void main(String[] args) {
		try {
			//Fájl beolvasása
			File xmlFile = new File("src/resources/XMLY4O4X0.xml");
			// Az XML dokumentum felépítése (DOM objektum létrehozása)
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			//Metódus hívások
			modifyEmployeeSalary(doc);
			addNewProjectToMarketingDepartment(doc);
			updateACostumerEmailAddress(doc);
			increaseNOTeamMembers(doc);

			//Módosítot xml fastruktúrában való kiírása
			printModifiedXml(doc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Csapat létszámának növelése
	private static void increaseNOTeamMembers(Document doc) {
		// A "csapat" elemek listájának lekérése
		 NodeList csapatok = doc.getElementsByTagName("csapat");
         for (int i = 0; i < csapatok.getLength(); i++) {
             Element csapat = (Element) csapatok.item(i);
             if (csapat.getAttribute("cskod").equals("cs2") && csapat.getAttribute("rkod").equals("r1")) {
                 Node letszam = csapat.getElementsByTagName("letszam").item(0);
                 int ujLetszam = Integer.parseInt(letszam.getTextContent()) + 100;
                 letszam.setTextContent(String.valueOf(ujLetszam));
             }
         }
	}

	// 3. Frissítsük egy ügyfél e-mail címét (ukod="u1")
	private static void updateACostumerEmailAddress(Document doc) {
		// A "ugyfel" elemek listájának lekérése
		 NodeList ugyfelek = doc.getElementsByTagName("ugyfel");
		 
         for (int i = 0; i < ugyfelek.getLength(); i++) {
             Element ugyfel = (Element) ugyfelek.item(i);
             if (ugyfel.getAttribute("ukod").equals("u1")) {
                 Node email = ugyfel.getElementsByTagName("email").item(0);
                 email.setTextContent("ujemail@domain.com");
             }
         }
	}

	// Hozzáadás a "Marketing" részleghez
	private static void addNewProjectToMarketingDepartment(Document doc) {
		// A "reszleg" elemek listájának lekérése
		NodeList reszlegek = doc.getElementsByTagName("reszleg");
		for (int i = 0; i < reszlegek.getLength(); i++) {
			Element reszleg = (Element) reszlegek.item(i);

			if (reszleg.getAttribute("rkod").equals("r2")) {
				// Új "projekt" létrehozása
				Element ujProjekt = doc.createElement("projekt");
				ujProjekt.setAttribute("pkod", "p10");
				ujProjekt.setAttribute("rkod", "r2");
				ujProjekt.setAttribute("ukod", "u6");

				Element nev = doc.createElement("nev");
				nev.setTextContent("XML beadandó készítés");
				ujProjekt.appendChild(nev);

				Element felelos = doc.createElement("felelos");
				felelos.setTextContent("Szalóczy Krisztián");
				ujProjekt.appendChild(felelos);

				Element hatarido = doc.createElement("hatarido");
				hatarido.setTextContent("2024-12-10");
				ujProjekt.appendChild(hatarido);

				// Az utolsó "projekt" után helyezés
				NodeList projektek = reszleg.getElementsByTagName("projekt");
				if (projektek.getLength() > 0) {
					Element utolsoProjekt = (Element) projektek.item(projektek.getLength() - 1);
					reszleg.insertBefore(ujProjekt, utolsoProjekt.getNextSibling());
				} else {
					// Ha nincs más projekt, a reszleg gyökérhez adja
					reszleg.appendChild(ujProjekt);
				}
			}
		}
	}

	//Módosított xml kiírása konzolra
	private static void printModifiedXml(Document doc) throws Exception{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		
		DOMSource source = new DOMSource(doc);
		StreamResult console = new StreamResult(System.out);
		System.out.println("-------Módosított fájl--------");
		transformer.transform(source, console);
}

	//Dolgozó fizetésének módosítása
	private static void modifyEmployeeSalary(Document doc) {
		// A "dolgozok" elemek listájának lekérése
		NodeList dolgozok = doc.getElementsByTagName("dolgozo");
		for(int i = 0; i < dolgozok.getLength(); i++) {
			Node dolgozo = dolgozok.item(i);
			
			if(dolgozo.getNodeType() == Node.ELEMENT_NODE) {
				Element dolgozoElement = (Element) dolgozo;
				if(dolgozoElement.getAttribute("dkod").equals("d1")) {
					Node fizetesNode = dolgozoElement.getElementsByTagName("fizetes").item(0);
					fizetesNode.setTextContent("500000");
				}
			}
		}	
	}
}
