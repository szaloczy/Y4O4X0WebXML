package y4o4x01112;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class JSONWriteY4O4X0 {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/main/resources/orarendY4O4X0.json")){
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONObject root = (JSONObject) object.get("SZK_orarend");
            JSONArray arr = (JSONArray) root.get("ora");

            System.out.println("Mérnökinformatikus órarend 2024/25 I. félév:\n");

            JSONObject output = new JSONObject();
            JSONArray outputArr = new JSONArray();

            for(int i = 0; i < arr.size(); i++){
                JSONObject lesson = (JSONObject) arr.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");
                System.out.println("\nTárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + " " + time.get("tol") + "-" + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));

                JSONObject lessonDetails = new JSONObject();
                lessonDetails.put("targy", lesson.get("targy"));
                lessonDetails.put("idopont", time);
                lessonDetails.put("helyszin", lesson.get("helyszin"));
                lessonDetails.put("oktato", lesson.get("oktato"));
                lessonDetails.put("szak", lesson.get("szak"));

                outputArr.add(lessonDetails);
            }

            output.put("SZK_orarend", outputArr);

            try(FileWriter writer = new FileWriter("src/main/resources/orarendY4O4X01.json")) {
                writer.write(JSONValue.toJSONString(output));
                writer.write("\n");

                // Alternatív mód, ha indentálni szeretnéd
                String prettyPrintedJson = JSONValue.toJSONString(output);  // True paraméterrel formázott kiírás

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
