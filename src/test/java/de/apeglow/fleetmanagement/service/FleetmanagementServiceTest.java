package de.apeglow.fleetmanagement.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FleetmanagementServiceTest {
	
	private FleetmanagementService fleetmanagementService;
	
	@Before
	public void setup() {
		fleetmanagementService = new FleetmanagementService();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan1000ScootersPerDistrict() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(18,1001), 12, 56);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan999ScootersToBeMaintainedByFM() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(18,78), 1000, 56);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void requiresFEToBeAbleToMaintainAtLeastOneScooter() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(18,78), 200, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void allowsNotMoreThan1000ScootersToBeMaintainedByFE() {
		fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(18,78), 567, 1001);
	}
	
	@Test
	public void fleetmanagementMaintainsAllIfOnlyOneDistrict() {
		int numberOfFE = fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(18), 30, 4);
		Assert.assertEquals(0, numberOfFE);
	}
	
	@Test
	public void fleetEngineerMaintainsAllOfDistrictIfNumberOfScooterWithinCapacity() {
		int numberOfFE = fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(70, 4), 70, 10);
		Assert.assertEquals(1, numberOfFE);
	}
	
	@Test
	public void twoFleetEngineersMaintainTheRestOfOneSingleDistrict() {
		int numberOfFE = fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(78), 50, 15);
		Assert.assertEquals(2, numberOfFE);
	}
	
	@Test
	public void fleetEngineersMaintainAllDistrictsButTheOneWithTheHighestSaving() {
		int numberOfFE = fleetmanagementService.calculateRequiredNumberOfFleetEngineers(sample(3, 4), 3, 2);
		Assert.assertEquals(2, numberOfFE);
	}
	
	
	private int[] sample(int ... numbersOfScootersPerDistrict) {
		return numbersOfScootersPerDistrict;
	}

}
