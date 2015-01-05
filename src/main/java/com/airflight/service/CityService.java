package com.airflight.service;

import java.util.Set;

import com.airflight.model.City;

public interface CityService {

	/**
	 * Finds all Cities and their routes to neighouring cities
	 * @return all Cities
	 */
	public Set<City> findAll();
}
