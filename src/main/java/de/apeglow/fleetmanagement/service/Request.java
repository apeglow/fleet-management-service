package de.apeglow.fleetmanagement.service;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Request {

	private int[] scooters;
	
	@SerializedName("C")
	private int c;
	
	@SerializedName("P")
	private int p;
}
