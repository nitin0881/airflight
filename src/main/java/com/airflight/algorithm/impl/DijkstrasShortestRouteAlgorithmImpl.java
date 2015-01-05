package com.airflight.algorithm.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.airflight.algorithm.ShortestRouteAlgorithm;
import com.airflight.model.City;
import com.airflight.model.Route;
import com.airflight.util.CityUtils;

public class DijkstrasShortestRouteAlgorithmImpl implements
		ShortestRouteAlgorithm {

	private String sourceCityName;
	private String destinationCityName;
	private City sourceCity;
	private Set<City> cities;

	//This list will hold the final shortest route to destination.
	private List<Route> shortestRootToDestination = new ArrayList<>();
	
	//Holds the identified shortest route from the source city to other cities. 
	private Map<String, RouteFromSource> closedShortestRoutesFromSource = new HashMap<>();
	
	//Holds the Routes which are visited from source route and are still pending to determine the next shortest route from source.
	//The next shortest route identified from this queue is removed and added the shortestRoutesFromSource
	private PriorityQueue<RouteFromSource> openRoutes = new PriorityQueue<>(10,new Comparator<RouteFromSource>() {
		public int compare(RouteFromSource route1,
				RouteFromSource route2) {			

			return route1.getDistanceFromSource() < route2
					.getDistanceFromSource() ? -1 : 1;
		};
	});
		
	public DijkstrasShortestRouteAlgorithmImpl(final Set<City> cities) {
		this.cities = cities;		
	}

	@Override
	public List<Route> findShortestRoute(final String sourceCityName,final String destinationCityName) {
		
		initialize(sourceCityName,destinationCityName);
		
		buildTheClosedRoutes(getSourceCityToSourceCityRoute(sourceCity));

		buildTheShortestRouteFromClosedRoutes(this.closedShortestRoutesFromSource
				.get(destinationCityName));
		
		return this.shortestRootToDestination;
	}
	
	private void initialize(final String sourceCityName,final String destinationCityName){
		this.sourceCityName = sourceCityName;
		this.destinationCityName = destinationCityName;
		this.sourceCity = CityUtils.getCityByName(sourceCityName, cities);
	}

	private RouteFromSource getSourceCityToSourceCityRoute(City sourceCity) {

		RouteFromSource traversedRoute = new RouteFromSource();
		Route route = new Route();
		route.setFromCity(sourceCity);
		route.setToCity(sourceCity);
		traversedRoute.setRoute(route);

		traversedRoute.setDistanceFromSource(0);

		this.openRoutes.add(traversedRoute);
		return traversedRoute;
	}

	/**
	 * Adds the neighbouring routes to the OpenRoutes list and identifies the next shortest route 
	 * The identified Shortest route is added to the ClosedRoute list and removed from the OpenRoutes list
	 * The method calls itself recursively until it finds the shortest route from a City to the Destination  
	 * @param nearestRouteFromSource the current nearestRoute in the OpenRoutes list  
	 */
	private void buildTheClosedRoutes(RouteFromSource nearestRouteFromSource) {

		City toCity = nearestRouteFromSource.getRoute().getToCity();

		for (Route route : toCity.getNeighbouringCities()) {

			double distanceFromSource = nearestRouteFromSource
					.getDistanceFromSource() + route.getDistance();

			RouteFromSource nextTraversedRoute = getVisitedRouteByCityName(route
					.getToCity().getName());

			if (nextTraversedRoute == null) {
				
				nextTraversedRoute = new RouteFromSource();
				nextTraversedRoute.setRoute(route);
				nextTraversedRoute.setDistanceFromSource(distanceFromSource);
				this.openRoutes.add(nextTraversedRoute);
				
			} else if (distanceFromSource < nextTraversedRoute
					.getDistanceFromSource()) {
				
				nextTraversedRoute.setRoute(route);
				nextTraversedRoute.setDistanceFromSource(distanceFromSource);
			}
		}

		this.openRoutes.remove(nearestRouteFromSource);
		this.closedShortestRoutesFromSource.put(nearestRouteFromSource.getRoute().getToCity()
				.getName(), nearestRouteFromSource);

		RouteFromSource nextNearestRoute = this.openRoutes.poll();

		if (nextNearestRoute.getRoute().getToCity().getName()
				.equals(destinationCityName)) {
			this.closedShortestRoutesFromSource.put(nextNearestRoute.getRoute().getToCity()
					.getName(), nextNearestRoute);
			return;
		}
		
		buildTheClosedRoutes(nextNearestRoute);
	}

	/**
	 * Gets the route from source by cityName
	 * @param cityName the city name
	 * @return the Route from Source city if present else null
	 */
	private RouteFromSource getVisitedRouteByCityName(String cityName) {

		for (RouteFromSource traversedRoute : this.openRoutes) {
			if (traversedRoute.getRoute().getToCity().getName()
					.equals(cityName)) {
				return traversedRoute;
			}
		}
		return null;
	}

	/**
	 * Populates the shortestRootToDestination list
	 * It retrieves the shortest routes from destination to source from by retrieving the routes from destination to source from the shortestRoutesFromSource Map
	 * @param nextShortestRouteFromDestination the next shortest route from destination
	 */
	private void buildTheShortestRouteFromClosedRoutes(
			RouteFromSource nextShortestRouteFromDestination) {

		shortestRootToDestination.add(0, nextShortestRouteFromDestination.getRoute());
		if (nextShortestRouteFromDestination.getRoute().getFromCity().getName()
				.equals(sourceCityName)) {
			return;
		}
		RouteFromSource previousShortestRoute = this.closedShortestRoutesFromSource
				.get(nextShortestRouteFromDestination.getRoute().getFromCity().getName());
		buildTheShortestRouteFromClosedRoutes(previousShortestRoute);
	}

}
