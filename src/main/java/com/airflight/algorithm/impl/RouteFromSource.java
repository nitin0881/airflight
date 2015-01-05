package com.airflight.algorithm.impl;

import com.airflight.model.Route;

public class RouteFromSource {

	private Route route;
	private double distanceFromSource;
	
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public double getDistanceFromSource() {
		return distanceFromSource;
	}
	public void setDistanceFromSource(double distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}
	
	@Override
	public String toString() {
		return "TraversedRoute [route=" + route + ", distanceFromSource="
				+ distanceFromSource + "]";
	}
	
	
	
}
