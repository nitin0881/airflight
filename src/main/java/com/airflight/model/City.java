package com.airflight.model;

import java.util.HashSet;
import java.util.Set;

public class City {
	private String name;
	private Set<Airport> airports=new HashSet<Airport>();
	private Set<Route> neighbouringCities=new HashSet<Route>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Airport> getAirports() {
		return airports;
	}
	public void setAirports(Set<Airport> airports) {
		this.airports = airports;
	}
	
	public Set<Route> getNeighbouringCities() {
		return neighbouringCities;
	}
	public void setNeighbouringCities(Set<Route> neighbouringCities) {
		this.neighbouringCities = neighbouringCities;
	}
	
	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}	
	
	
}
