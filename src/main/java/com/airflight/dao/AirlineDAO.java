package com.airflight.dao;

import com.airflight.model.Airline;

public interface AirlineDAO {
	
	public Airline findByCode(String code);
}
