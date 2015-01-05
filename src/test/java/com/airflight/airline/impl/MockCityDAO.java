package com.airflight.airline.impl;

import java.util.HashSet;
import java.util.Set;

import com.airflight.dao.CityDAO;
import com.airflight.model.City;
import com.airflight.model.Route;

public class MockCityDAO implements CityDAO {

	@Override
	public Set<City> findAll() {

		
		//
		// 	 Mumbai----------1400--------------
		// /150    \ 929 					   |
		// Pune      Jaipur ------271----------Delhi
		// \170     / 670                      |
		//   Ahemdabad---------950--------------

		Set<City> cities=new HashSet<>();
		
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

		return cities;
	}

}
