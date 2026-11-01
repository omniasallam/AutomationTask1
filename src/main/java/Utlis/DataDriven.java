package Utlis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
public class DataDriven {
    public static JsonNode jsonReader() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            return objectMapper.readTree(
                    new File("testData/testData.json")
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not read testData.json", e
            );
        }
    }

}
