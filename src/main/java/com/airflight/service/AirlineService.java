package com.airflight.service;

import java.util.List;

import com.airflight.algorithm.ShortestRouteAlgorithm;
import com.airflight.dao.AirlineDAO;
import com.airflight.exception.AirlineCodeIncorrectException;
import com.airflight.exception.NotOperatingInSpecifiedCitiesException;
import com.airflight.model.Airline;
import com.airflight.model.Route;

public interface AirlineService {

	public Airline findByCode(String code);
	
	/**
	 * Finds the Shortest route between fromCity and toCity
	 * @param airlineCode the airlineCode
	 * @param sourceCity the source city
	 * @param destinationCity the destination city
	 * @return the Shortest Route 
	 * @throws AirlineCodeIncorrectException
	 * @throws NotOperatingInSpecifiedCitiesException
	 */
	public List<Route> findShortestRoute(String airlineCode,String sourceCity,String destinationCity) throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException;
	
	public void setAirlineDAO(AirlineDAO airlineDAO);
	
	public void setShortestRouteAlgorithm(ShortestRouteAlgorithm shortestRouteAlgorithm);
}
