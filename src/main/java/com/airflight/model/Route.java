package com.airflight.model;

public class Route {
	
	private City fromCity;
	private City toCity;
	private double distance;
	
	public Route(){
		
	}
	
	public Route(City fromCity,City toCity, double distance){
		this.fromCity=fromCity;
		this.toCity=toCity;
		this.distance=distance;
	}
	
	public City getFromCity() {
		return fromCity;
	}
	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}
	public City getToCity() {
		return toCity;
	}
	public void setToCity(City toCity) {
		this.toCity = toCity;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Route [fromCity=" + fromCity + ", toCity=" + toCity
				+ ", distance=" + distance + "]";
	}	
	
}
