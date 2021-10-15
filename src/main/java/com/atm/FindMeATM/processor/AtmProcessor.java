package com.atm.FindMeATM.processor;

import java.io.InputStream;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URL;
import java.io.BufferedReader;
import java.util.List;

public class AtmProcessor {

    public List<JSONArray> buscaJSON(String cp) {

        URL url;
        HttpsURLConnection connection;
        searchingATMProcessor busca = new searchingATMProcessor();
        List<JSONArray> results = null;

        String HTTPS_URL = "https://www.banamex.com/localizador/jsonP/json5.json";

        try {
            url = new URL(HTTPS_URL);
            connection = (HttpsURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String jsonText = br.readLine();
            jsonText = jsonText.replaceAll("(jsonCallback\\()", "");
            jsonText = jsonText.replaceAll("(\\);)", "");

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonText);

             results = busca.findMe(jsonObject, cp);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}

