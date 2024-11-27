package xpathy4o4x01119;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.validation.Schema;

import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONValidationY4O4X0 {

	public static void main(String[] args) {
		String schemaPath = "src/main/resources/orarendY4O4X0Schema.json";
		String jsonPath = "src/main/resources/orarendY4O4X0.json";
		{
		
		try(InputStream schemaStream = new FileInputStream(schemaPath);
			InputStream jsonStream = new FileInputStream(jsonPath)) {
			
			JSONObject schemaJson = new JSONObject(new JSONTokener(schemaStream));
			Schema schema = SchemaLoader.load(schemaJson);
			
			JSONObject json = new JSONObject(new JSONTokener(jsonStream));
			
			schema.validate(json);
			System.out.println("A JSON érvényes a séma alapján");
			
		}catch(Exception e) {
			System.out.println("Hiba történt a validáció során.");
			System.out.println(e.getMessage());
		}
		}
	}

}
