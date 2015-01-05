package com.airflight.service.impl;

import java.util.List;

import com.airflight.algorithm.ShortestRouteAlgorithm;
import com.airflight.dao.AirlineDAO;
import com.airflight.exception.AirlineCodeIncorrectException;
import com.airflight.exception.NotOperatingInSpecifiedCitiesException;
import com.airflight.model.Airline;
import com.airflight.model.Route;
import com.airflight.service.AirlineService;

public class AirlineServiceImpl implements AirlineService {

	private AirlineDAO airlineDAO;
	private ShortestRouteAlgorithm shortestRouteAlgorithm;
	
	@Override
	public Airline findByCode(final String code) {
		return airlineDAO.findByCode(code);
	}

	@Override
	public List<Route> findShortestRoute(final String airlineCode,final String sourceCity, final String destinationCity) throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException {
		validateFindShortestRouteInput(airlineCode,sourceCity,destinationCity);
		return shortestRouteAlgorithm.findShortestRoute(sourceCity, destinationCity);
	}
	
	private void validateFindShortestRouteInput(String airlineCode,String sourceCity,String destinationCity) throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException {
		Airline airline=airlineDAO.findByCode(airlineCode);
		
		if(airline==null){
			throw new AirlineCodeIncorrectException();
		}
		
		boolean isOperatingFromSourceCity=false;
		boolean isOperatingFromDestinationCity=false;
		
		for(String city:airline.getOperationalCities()){
			if(city.equals(sourceCity)){
				isOperatingFromSourceCity=true;
			}else if(city.equals(destinationCity)){
				isOperatingFromDestinationCity=true;
			}
		}
		
		if(!isOperatingFromDestinationCity||!isOperatingFromSourceCity){
			throw new NotOperatingInSpecifiedCitiesException();
		}		
	}

	public AirlineDAO getAirlineDAO() {
		return airlineDAO;
	}

	public void setAirlineDAO(AirlineDAO airlineDAO) {
		this.airlineDAO = airlineDAO;
	}

	public ShortestRouteAlgorithm getShortestRouteAlgorithm() {
		return shortestRouteAlgorithm;
	}

	public void setShortestRouteAlgorithm(
			ShortestRouteAlgorithm shortestRouteAlgorithm) {
		this.shortestRouteAlgorithm = shortestRouteAlgorithm;
	}	

}
