package com.airflight.service.impl;

import java.util.Set;

import com.airflight.dao.CityDAO;
import com.airflight.model.City;
import com.airflight.service.CityService;

public class CityServiceImpl implements CityService {

	private CityDAO cityDAO;
	
	@Override
	public Set<City> findAll() {
		return cityDAO.findAll();
	}

	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	

}
