import domy4o4x01015.DOMRead;

public class Main {
    public static void main(String[] args) {

        DOMRead dm = new DOMRead("src/resources/Y4O4X0_orarend.xml");
        try {
            dm.query(dm.buildUpDom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}