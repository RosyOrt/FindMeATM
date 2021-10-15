package com.atm.FindMeATM.controller;

import java.util.List;
import com.atm.FindMeATM.processor.AtmProcessor;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
	AtmProcessor llamarProcessor = new AtmProcessor();

	@GetMapping(path = "/FindMeATM")
	public List<JSONArray> buscarCajero(@RequestParam(value = "cp", required = true)String cp){

		List<JSONArray> results = llamarProcessor.buscaJSON(cp);

		 results.forEach(System.out::println);

		 return results;
	}
}
