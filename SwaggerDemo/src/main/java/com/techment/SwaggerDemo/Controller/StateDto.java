package com.techment.SwaggerDemo.Controller;

import java.util.List;

public class StateDto {

	private String stateName;
	private List<String> cities;
	
	protected StateDto() { }
	


	public StateDto(String stateName, List<String> cities) {
		this.stateName = stateName;
		this.cities = cities;
	}



	public String getStateName() {
		return stateName;
	}

	public List<String> getCities() {
		return cities;
	}

}
