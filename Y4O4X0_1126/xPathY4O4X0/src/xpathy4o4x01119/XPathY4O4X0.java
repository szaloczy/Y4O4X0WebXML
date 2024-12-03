package xpathy4o4x01119;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathY4O4X0 {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("src/resources/studentY4O4X0.xml");
			Document doc = buildUpDocument(xmlFile);
			
			// XPath objektum létrehozása
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			// 1. Összes student elem, amely a class gyermekei
			System.out.println("1. Összes student elem: ");
			NodeList students = (NodeList) xPath.evaluate("/class/student", doc, XPathConstants.NODESET);
			printNodeList(students);
			
			// 2. Student elem, amely "id" attribútummal rendelkezik és értéke "s2"
			System.out.println("\n2. Student 'id' attribútum értéke 's2':");
	        Node studentWithId02 = (Node) xPath.evaluate("/class/student[@id='s2']", doc, XPathConstants.NODE);
	        printNode(studentWithId02);
	        
	        // 3. Összes student elem, függetlenül a helytől
	        System.out.println("\n3. Összes student elem függetlenül a helytől: ");
	        NodeList allStudent = (NodeList) xPath.evaluate("//student", doc, XPathConstants.NODESET);
	        printNodeList(allStudent);
	        
	        // 4. Második student elem a class root elem gyermekei között
	        System.out.println("\n4. Második student elem: ");
	        Node secondStudent = (Node) xPath.evaluate("/class/student[2]", doc, XPathConstants.NODE);
	        printNode(secondStudent);
	        
	        // 5. Utolsó student elem
	        System.out.println("\n5. Utólsó student elem: ");
	        Node lastStudent = (Node) xPath.evaluate("/class/student[last()]", doc, XPathConstants.NODE);
	        printNode(lastStudent);
	        
	        // 6. Utolsó előtti student elem
	        System.out.println("\n6. Utolsó előtti student elem:");
	        Node secondLastStudent = (Node) xPath.evaluate("/class/student[last()-1]", doc, XPathConstants.NODE);
	        printNode(secondLastStudent);
	        
	        // 7. Első két student elem
            System.out.println("\n7. Első két student elem:");
            NodeList firstTwoStudent = (NodeList) xPath.evaluate("/class/student[position() <= 2]", doc, XPathConstants.NODESET);
            printNodeList(firstTwoStudent);
            
            // 8. Class root elem összes gyermeke
            System.out.println("\n8. Class root elem összes gyermeke:");
            NodeList allChildElement = (NodeList) xPath.evaluate("/class/*", doc, XPathConstants.NODESET);
            printNodeList(allChildElement);
            
            // 9. Összes student elem attribútummal
            System.out.println("\n9. Összes student elem, amely rendelkezik attribútummal:");
            NodeList studentsWithId = (NodeList) xPath.evaluate("/class/student[@*]", doc, XPathConstants.NODESET);
            printNodeList(studentsWithId);
            
            // 10. Dokumentum összes eleme
            System.out.println("\n10. Dokumentum összes eleme:");
            NodeList allElements = (NodeList) xPath.evaluate("//*", doc, XPathConstants.NODESET);
            printNodeList(allElements);
            
            // 11. Student elem, amelynél a kor > 20
            System.out.println("\n11. Student elemek, amelyeknél kor > 20:");
            NodeList studentOlderThan20 = (NodeList) xPath.evaluate("/class/student[kor > 20]", doc, XPathConstants.NODESET);
            printNodeList(studentOlderThan20);
            
            // 12. Összes keresztneve a student elemeknek
            System.out.println("\n12. Student keresztnevek:");
            NodeList allFirstName = (NodeList) xPath.evaluate("/class/student/keresztnev/text()", doc, XPathConstants.NODESET);
            printNodeList(allFirstName);
            
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	private static void printNodeList(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			printNode(nodeList.item(i));
		}
	}

	
	private static void printNode(Node node){
		if (node != null) {
			System.out.println(node.getTextContent().trim());
		} else {
			System.out.println("Nincs ilyen elem");
		}
	}
	
	private static Document buildUpDocument(File file) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(file);
		doc.getDocumentElement().normalize();
	
		return doc;
	}

}
