import domy4o4x01015.DOMRead;
import domy4o4x01015.DomWriteY4O4X0;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/resources/Y4O4X0_orarend.xml";

        DOMRead dm = new DOMRead(path);
        Document dom = dm.buildUpDom();
        DomWriteY4O4X0 write = new DomWriteY4O4X0(dom.getDocumentElement());

        try {
            write.printXmlStructure(dom.getDocumentElement(),"");
            write.writeXmlFile(dom, "src/resources/Y4O4X0_orarend1.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}