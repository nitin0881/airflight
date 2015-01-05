package com.airflight.airline.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.airflight.algorithm.ShortestRouteAlgorithm;
import com.airflight.algorithm.impl.DijkstrasShortestRouteAlgorithmImpl;
import com.airflight.dao.AirlineDAO;
import com.airflight.dao.CityDAO;
import com.airflight.exception.AirlineCodeIncorrectException;
import com.airflight.exception.NotOperatingInSpecifiedCitiesException;
import com.airflight.model.Route;
import com.airflight.service.AirlineService;
import com.airflight.service.impl.AirlineServiceImpl;

public class AirlineServiceTest {

	private static AirlineDAO airlineDAO;
	private static CityDAO cityDAO;
	private static ShortestRouteAlgorithm shortestRouteAlgorithm; 
	private static AirlineService airlineService;
	
	@BeforeClass
	public static void setup(){
		airlineDAO=new MockAirlineDAO();
		cityDAO=new MockCityDAO();
		shortestRouteAlgorithm=new DijkstrasShortestRouteAlgorithmImpl(cityDAO.findAll());
		
		airlineService=new AirlineServiceImpl();
		airlineService.setAirlineDAO(airlineDAO);
		airlineService.setShortestRouteAlgorithm(shortestRouteAlgorithm);
	}
	
	@Test
	public void testShortestRoute() throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException{
		List<Route> shortestRoute = airlineService.findShortestRoute("Indigo", "Pune", "Delhi");
		
		Assert.assertNotNull(shortestRoute);
		Assert.assertEquals(3, shortestRoute.size());

		// Check the first route is from Pune To Ahemdabad
		Assert.assertEquals("Pune", shortestRoute.get(0).getFromCity()
				.getName());
		Assert.assertEquals("Ahemdabad", shortestRoute.get(0).getToCity()
				.getName());

		// Check the next route is from Ahemdabad To Jaipur
		Assert.assertEquals("Ahemdabad", shortestRoute.get(1).getFromCity()
				.getName());
		Assert.assertEquals("Jaipur", shortestRoute.get(1).getToCity()
				.getName());

		// Check the next route is from Jaipur To Delhi
		Assert.assertEquals("Jaipur", shortestRoute.get(2).getFromCity()
				.getName());
		Assert.assertEquals("Delhi", shortestRoute.get(2).getToCity().getName());
	}
	
	@Test(expected=NotOperatingInSpecifiedCitiesException.class)
	public void testNotOperatingCitiesException() throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException{
		airlineService.findShortestRoute("Indigo", "Pune", "Kerla");
	}
	
	@Test(expected=AirlineCodeIncorrectException.class)
	public void testAirlineCodeIncorrectException() throws AirlineCodeIncorrectException, NotOperatingInSpecifiedCitiesException{
		airlineService.findShortestRoute("AirIndia", "Pune", "Mumbai");
	}
	
}
