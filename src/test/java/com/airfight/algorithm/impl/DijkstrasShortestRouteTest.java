package com.airfight.algorithm.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.airflight.algorithm.ShortestRouteAlgorithm;
import com.airflight.algorithm.impl.DijkstrasShortestRouteAlgorithmImpl;
import com.airflight.model.City;
import com.airflight.model.Route;

public class DijkstrasShortestRouteTest {

	private static Set<City> cities = new HashSet<>();

	@BeforeClass
	public static void setup() {
		buildCitiesData();
	}

	private static void buildCitiesData() {

		//
		// 	 Mumbai----------1400--------------
		// /150    \ 929 					   |
		// Pune      Jaipur ------271----------Delhi
		// \170     / 670                      |
		//   Ahemdabad---------950--------------

		City puneCity = new City();
		puneCity.setName("Pune");
		cities.add(puneCity);

		City mumbaiCity = new City();
		mumbaiCity.setName("Mumbai");
		cities.add(mumbaiCity);

		City ahemdabadCity = new City();
		ahemdabadCity.setName("Ahemdabad");
		cities.add(ahemdabadCity);

		City jaipurCity = new City();
		jaipurCity.setName("Jaipur");
		cities.add(jaipurCity);

		City delhiCity = new City();
		delhiCity.setName("Delhi");
		cities.add(delhiCity);

		Route puneToMumbaiRoute = new Route(puneCity, mumbaiCity, 150);
		Route puneToAhemdabadRoute = new Route(puneCity, ahemdabadCity, 170);

		Set<Route> puneNeighbouringCites = new HashSet<>();
		puneNeighbouringCites.add(puneToMumbaiRoute);
		puneNeighbouringCites.add(puneToAhemdabadRoute);
		puneCity.setNeighbouringCities(puneNeighbouringCites);

		Route mumbaiToJaipurRoute = new Route(mumbaiCity, jaipurCity, 929);
		Route mumbaiToDelhiRoute = new Route(mumbaiCity, delhiCity, 1400);

		Set<Route> mumbaiNeighbouringCites = new HashSet<>();
		mumbaiNeighbouringCites.add(mumbaiToDelhiRoute);
		mumbaiNeighbouringCites.add(mumbaiToJaipurRoute);
		mumbaiCity.setNeighbouringCities(mumbaiNeighbouringCites);

		Route ahemdabadToJaipurRoute = new Route(ahemdabadCity, jaipurCity, 670);
		Route ahemdabadToDelhiRoute = new Route(ahemdabadCity, delhiCity, 950);

		Set<Route> ahemdabadNeighbouringCites = new HashSet<>();
		ahemdabadNeighbouringCites.add(ahemdabadToJaipurRoute);
		ahemdabadNeighbouringCites.add(ahemdabadToDelhiRoute);
		ahemdabadCity.setNeighbouringCities(ahemdabadNeighbouringCites);

		Route jaipurToDelhiRoute = new Route(jaipurCity, delhiCity, 271);
		Set<Route> jaipurNeighbouringCites = new HashSet<>();
		jaipurNeighbouringCites.add(jaipurToDelhiRoute);
		jaipurCity.setNeighbouringCities(jaipurNeighbouringCites);

	}

	@Test
	public void testShortestRouteFromPuneToDelhi() {

		ShortestRouteAlgorithm shortestRouteAlgorithm = new DijkstrasShortestRouteAlgorithmImpl(
				 cities);
		List<Route> shortestRoute = shortestRouteAlgorithm.findShortestRoute("Pune", "Delhi");

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

	@Test
	public void testShortestRouteFromPuneToJaipur() {

		ShortestRouteAlgorithm shortestRouteAlgorithm = new DijkstrasShortestRouteAlgorithmImpl(cities);
		List<Route> shortestRoute = shortestRouteAlgorithm.findShortestRoute("Pune", "Jaipur");

		Assert.assertNotNull(shortestRoute);
		Assert.assertEquals(2, shortestRoute.size());

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

	}

	@Test
	public void testShortestRouteFromMumbaiToDelhi() {

		ShortestRouteAlgorithm shortestRouteAlgorithm = new DijkstrasShortestRouteAlgorithmImpl(cities);
		List<Route> shortestRoute = shortestRouteAlgorithm.findShortestRoute("Mumbai", "Delhi");

		Assert.assertNotNull(shortestRoute);
		Assert.assertEquals(2, shortestRoute.size());

		// Check the first route is from Pune To Ahemdabad
		Assert.assertEquals("Mumbai", shortestRoute.get(0).getFromCity()
				.getName());
		Assert.assertEquals("Jaipur", shortestRoute.get(0).getToCity()
				.getName());

		// Check the next route is from Ahemdabad To Jaipur
		Assert.assertEquals("Jaipur", shortestRoute.get(1).getFromCity()
				.getName());
		Assert.assertEquals("Delhi", shortestRoute.get(1).getToCity()
				.getName());
	}

	@Test
	public void testShortestRouteFromAhmedabadToDelhi() {
		buildCitiesData();
		ShortestRouteAlgorithm shortestRouteAlgorithm = new DijkstrasShortestRouteAlgorithmImpl(cities);
		List<Route> shortestRoute = shortestRouteAlgorithm.findShortestRoute("Ahemdabad", "Delhi");

		Assert.assertNotNull(shortestRoute);
		Assert.assertEquals(2, shortestRoute.size());

		// Check the first route is from Pune To Ahemdabad
		Assert.assertEquals("Ahemdabad", shortestRoute.get(0).getFromCity()
				.getName());
		Assert.assertEquals("Jaipur", shortestRoute.get(0).getToCity()
				.getName());

		// Check the next route is from Ahemdabad To Jaipur
		Assert.assertEquals("Jaipur", shortestRoute.get(1).getFromCity()
				.getName());
		Assert.assertEquals("Delhi", shortestRoute.get(1).getToCity()
				.getName());

	}

}
