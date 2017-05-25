package de.apeglow.fleetmanagement.service;

public class FleetmanagementService {
	private static final int MAX_SCOOTERS_PER_DISTRICT = 1000;
	private static final int MAX_SCOOTERS_OF_FM = 999;
	private static final int MAX_SCOOTERS_OF_FE = 1000;
	
	public int calculateRequiredNumberOfFleetEngineers(int[] numberOfScootersPerDistrict, int numberOfScootersMaintainableByFM, int numberOfScootersMaintainableByFE) {
		validateInput(numberOfScootersPerDistrict, numberOfScootersMaintainableByFM, numberOfScootersMaintainableByFE);
		
		
		return 0;
	}

	private void validateInput(int[] numberOfScootersPerDistrict, int numberOfScootersMaintainableByFM,
			int numberOfScootersMaintainableByFE) {
		for (int district=0; district<numberOfScootersPerDistrict.length; district++) {
			if (numberOfScootersPerDistrict[district] > MAX_SCOOTERS_PER_DISTRICT) {
				throw new IllegalArgumentException("District "+district+" has more than "+MAX_SCOOTERS_PER_DISTRICT+" scooters.");
			}
		}
		
		if (numberOfScootersMaintainableByFM > MAX_SCOOTERS_OF_FM) {
			throw new IllegalArgumentException("Number of scooters maintainable by FM exceeds "+MAX_SCOOTERS_OF_FM+".");
		}
		
		if (numberOfScootersMaintainableByFE > MAX_SCOOTERS_OF_FE) {
			throw new IllegalArgumentException("Number of scooters maintainable by FE exceeds "+MAX_SCOOTERS_OF_FE+".");
		}
	}

}
