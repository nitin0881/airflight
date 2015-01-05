package com.airflight.model;

import java.util.HashSet;
import java.util.Set;

public class Airline {
	
	private String code;
	private String name;
	private String company;
	private Set<String> operationalCities=new HashSet<String>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public Set<String> getOperationalCities() {
		return operationalCities;
	}
	public void setOperationalCities(Set<String> operationalCities) {
		this.operationalCities = operationalCities;
	}

}
