package de.apeglow.fleetmanagement.service;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Response {

	@SerializedName("fleet_engineers")
	private int numberOfEngineers;
	
	public Response(int numberOfEngineers) {
		this.numberOfEngineers = numberOfEngineers;
	}
}
