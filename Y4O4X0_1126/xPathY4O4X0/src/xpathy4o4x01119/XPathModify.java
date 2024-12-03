package xpathy4o4x01119;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathModify {

	public static void main(String[] args) {
		try {
			File file = new File("src/resources/Y4O4X0_orarend.xml");
			Document doc = buildUpDocument(file);
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			modifyProfession(doc, xPath);
			appendSignitureToSubjectNames(doc, xPath);
			modifyId3ElementLocation(doc, xPath);
			
			printToFileAndConsole(doc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void modifyId3ElementLocation(Document doc, XPath xPath) throws Exception {
		 System.out.println("3. ID=O3 helyszínének módosítása...");
         Node helyszinElem = (Node) xPath.evaluate("//ora[@id='O3']/helyszin", doc, XPathConstants.NODE);
         if (helyszinElem != null) {
             helyszinElem.setTextContent("XXXVII");
         } else {
             System.out.println("Nem található az ID=O3 helyszín!");
         }
	}
	
	private static void appendSignitureToSubjectNames(Document doc, XPath xPath) throws Exception{
		System.out.println("2. Tárgy nevéhez monogramm hozzáfűzése: ");
		NodeList nodeList = (NodeList) xPath.evaluate("//targy", doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = (Node) nodeList.item(i);
			String text = node.getTextContent();
			node.setTextContent(text + " (" + text.charAt(0) + text.charAt(text.length()-1)+ ")");
		}
	}
	
	private static void modifyProfession(Document doc, XPath xPath) throws Exception{
		System.out.println("1. Szak nevének módosítása");
		NodeList nodeList = (NodeList) xPath.evaluate("//szak", doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			node.setTextContent("Árnyékolás technikus");
		}
	}
	
	private static Document buildUpDocument(File file) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(file);
		doc.getDocumentElement().normalize();
		
		return doc;
	}
	
	private static void printToFileAndConsole(Document doc) throws Exception {
		File outputFile = new File("src/resources/Y4O4X0_orarend2.xml");
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DOMSource source = new DOMSource(doc);
		StreamResult outFile = new StreamResult(outputFile);
		StreamResult console = new StreamResult(System.out);
		transformer.transform(source, outFile);
		System.out.println("---------------Módosított fájl--------------------");
		transformer.transform(source, console);
		System.out.println("Sucessfully written to " + outputFile);
	}

}
