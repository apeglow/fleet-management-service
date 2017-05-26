package de.apeglow.fleetmanagement.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

public class FleetmanagementServiceApplication {
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Missing input file name.");
		}
		
		
		Gson gson = new Gson();
		
		try (Reader inputFileReader = new FileReader(new File(args[0]))) {
			
			Request request = gson.fromJson(inputFileReader, Request.class);
			
			FleetmanagementService fleetmanagementService = new FleetmanagementService();
			
			int numberOfEngineers = fleetmanagementService.calculateRequiredNumberOfFleetEngineers(request.getScooters(), request.getC(), request.getP());
			
			System.out.println(gson.toJson(new Response(numberOfEngineers)));
		}
		
	}

}
