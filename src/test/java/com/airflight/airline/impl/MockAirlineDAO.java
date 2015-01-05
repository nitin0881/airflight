package com.airflight.airline.impl;

import java.util.HashSet;
import java.util.Set;

import com.airflight.dao.AirlineDAO;
import com.airflight.model.Airline;

public class MockAirlineDAO implements AirlineDAO {

	@Override
	public Airline findByCode(String code) {
		if(code.equalsIgnoreCase("Indigo")){
			Airline airline=new Airline();
			airline.setCode("Indigo");
			airline.setName("Indigo");
			
			Set<String>operationalCities=new HashSet<>();
			operationalCities.add("Pune");
			operationalCities.add("Mumbai");
			operationalCities.add("Jaipur");
			operationalCities.add("Delhi");
			operationalCities.add("Ahemdabad");
			
			airline.setOperationalCities(operationalCities);
			
			return airline;
		}
		return null;
	}

}
