package de.apeglow.fleetmanagement.service;

import org.junit.Before;
import org.junit.Test;

public class FleetmanagementServiceTest {
	
	private FleetmanagementService fleetmanagementService;
	
	
	private int[] numberOfScootersPerDistrict;
	
	@Before
	public void setup() {
		fleetmanagementService = new FleetmanagementService();
		
		numberOfScootersPerDistrict = new int[2];
		numberOfScootersPerDistrict[0] = 18;
		numberOfScootersPerDistrict[1] = 78;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan1000ScootersPerDistrict() {
		int[] numberOfScootersPerDistrict = new int[2];
		numberOfScootersPerDistrict[0] = 18;
		numberOfScootersPerDistrict[1] = 1001;
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(numberOfScootersPerDistrict, 12, 56);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan999ScootersToBeMaintainedByFM() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(numberOfScootersPerDistrict, 1000, 56);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan1000ScootersToBeMaintainedByFE() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(numberOfScootersPerDistrict, 567, 1001);
	}

}
