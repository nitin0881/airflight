package com.airflight.algorithm;

import java.util.List;

import com.airflight.model.Route;

public interface ShortestRouteAlgorithm {
	
	/**
	 * Finds the Shortest route 
	 * @return
	 */
	public List<Route> findShortestRoute(String sourceCity,String destinationCity); 

}
