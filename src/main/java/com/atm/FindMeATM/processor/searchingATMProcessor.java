package com.atm.FindMeATM.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class searchingATMProcessor {

	public List<JSONArray> findMe(JSONObject jsonObject, String cp) throws Exception {

        List<JSONObject> list = new ArrayList<>();
        List<JSONArray> results = null;
        try {
            JSONObject jsonServicios =  (JSONObject)jsonObject.get("Servicios");

            jsonServicios.forEach((k,v) -> list.add((JSONObject)v));

            List<JSONObject> ListServices = (List<JSONObject>)list.stream()
                    .flatMap(jsonObjectServices -> jsonObjectServices.values().parallelStream())
                    .collect(Collectors.toList());

            List<JSONArray> arrays = (List<JSONArray>)ListServices.stream()
                    .flatMap(jsonObjectServices -> jsonObjectServices.values().parallelStream())
                    .collect(Collectors.toList());

            results = arrays.stream()
                    .parallel()
                    .filter(item -> item.get(4).toString().contains(cp))
                    .collect(Collectors.toList());

        }catch (Exception e){
            throw new Exception("Ocurrio exception durante filtrado" + e.getMessage());
        }
        return results;
    }
}
