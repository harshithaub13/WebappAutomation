package com.mishipay.webshopper.testData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class JsonUtil {
	public static List<Object[]> readJsonData(String jsonFilePath, String storeType){
        List<Object[]> barcodeList = new ArrayList<>();
        try {
            // Validate file existence before reading
            File file = new File(jsonFilePath);
            if (!file.exists()) {
                throw new FileNotFoundException("JSON file not found at: " + jsonFilePath);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(file);

            if (rootNode.has(storeType)) {
                for (JsonNode barcode : rootNode.get(storeType)) {
                    barcodeList.add(new Object[]{barcode.asText()});
                }
            } else {
                System.out.println("⚠️ Store type not found in JSON: " + storeType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barcodeList;
    }
}
