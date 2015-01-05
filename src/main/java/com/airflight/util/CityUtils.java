package com.airflight.util;

import java.util.Set;

import com.airflight.model.City;

public class CityUtils {

	public static City getCityByName(String cityName, Set<City> cities) {

		for (City city : cities) {
			if (city.getName().equalsIgnoreCase(cityName)) {
				return city;
			}
		}

		return null;
	}

}
