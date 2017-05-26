package de.apeglow.fleetmanagement.service;

public class FleetmanagementService {
	private static final int MAX_SCOOTERS_PER_DISTRICT = 1000;
	private static final int MAX_SCOOTERS_OF_FM = 999;
	private static final int MAX_SCOOTERS_OF_FE = 1000;
	
	
	/**
	 * Calculates the number of required fleet engineers.
	 * 
	 * @param numberOfScootersPerDistrict
	 * @param numberOfScootersMaintainableByFM
	 * @param numberOfScootersMaintainableByFE
	 * 
	 * @return number of required fleet engineers
	 */
	public int calculateRequiredNumberOfFleetEngineers(int[] numberOfScootersPerDistrict, int numberOfScootersMaintainableByFM, int numberOfScootersMaintainableByFE) {
		validateInput(numberOfScootersPerDistrict, numberOfScootersMaintainableByFM, numberOfScootersMaintainableByFE);
		
		
		/*
		 * This logic here finds out to which district we should assign the FM to in order to reduce the number of additional fleet engineers to
		 * the minimum.
		 * 
		 * Example: District 1 has 3 scooters
		 * 			District 2 has 4 scooters
		 * 			FM can handle 3 scooters
		 * 			FE can handle 2 scooters
		 * 
		 * -> We should assign FM to district 1
		 * 
		 */
		int[] numberOfSavedEngineers = new int[numberOfScootersPerDistrict.length];
		
		int numberOfFE = 0;
		for (int district=0; district<numberOfScootersPerDistrict.length; district++) {
			int numberOfScooters = numberOfScootersPerDistrict[district];
			
			int numberOfFEForDistrict = ((Double)Math.ceil((double)numberOfScooters/numberOfScootersMaintainableByFE)).intValue();
			numberOfFE = numberOfFE + numberOfFEForDistrict;
			
			
			int numberOfFEForDistrictIfItWasTheMainDistrict = ((Double)Math.ceil((double)(numberOfScooters-numberOfScootersMaintainableByFM)/numberOfScootersMaintainableByFE)).intValue();
			if (numberOfFEForDistrictIfItWasTheMainDistrict < 0) {
				numberOfFEForDistrictIfItWasTheMainDistrict = 0;
			}
			
			numberOfSavedEngineers[district] = numberOfFEForDistrict - numberOfFEForDistrictIfItWasTheMainDistrict;
		}
		
		int districtWithBiggestSaving = findBiggest(numberOfSavedEngineers);
		
		return numberOfFE - numberOfSavedEngineers[districtWithBiggestSaving];
	}
	
	private int findBiggest(int[] toCheck) {
		int biggest = -1;
		
		for (int district=0; district<toCheck.length; district++) {
			int numberOfScooters = toCheck[district];
			if (biggest<0 || numberOfScooters > toCheck[biggest]) {
				biggest = district;
			}
		}
		
		return biggest;
	}

	private void validateInput(int[] numberOfScootersPerDistrict, int numberOfScootersMaintainableByFM, int numberOfScootersMaintainableByFE) {
		if (numberOfScootersMaintainableByFE <= 0) {
			throw new IllegalArgumentException("Number of scooters maintainable by FE must be at least 1.");
		}
		
		if (numberOfScootersMaintainableByFM < 0) {
			throw new IllegalArgumentException("Number of scooters maintainable by FM must be at least 0.");
		}
		
		if (numberOfScootersMaintainableByFM > MAX_SCOOTERS_OF_FM) {
			throw new IllegalArgumentException("Number of scooters maintainable by FM exceeds "+MAX_SCOOTERS_OF_FM+".");
		}
		
		if (numberOfScootersMaintainableByFE > MAX_SCOOTERS_OF_FE) {
			throw new IllegalArgumentException("Number of scooters maintainable by FE exceeds "+MAX_SCOOTERS_OF_FE+".");
		}
		
		
		for (int district=0; district<numberOfScootersPerDistrict.length; district++) {
			if (numberOfScootersPerDistrict[district] > MAX_SCOOTERS_PER_DISTRICT) {
				throw new IllegalArgumentException("District "+district+" has more than "+MAX_SCOOTERS_PER_DISTRICT+" scooters.");
			}
		}
			
	}

}
