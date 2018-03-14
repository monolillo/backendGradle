package com.hdsupply.xmi.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AssetLocation {
	
	private double longitude;
	private double latitude;
	private Place place;
	private LocationMode mode = LocationMode.NONE;
	private double accuracy;
	private Date timestamp;
	
	@JsonIgnore
	private Place newPlace;

	public AssetLocation() {
		
	}

	public AssetLocation(Place place) {
		this.place = place;
		this.longitude = place.getLongitude();
		this.latitude = place.getLatitude();
		this.timestamp = new Date();
	}
	
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
	 * @return the mode
	 */
	public LocationMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(LocationMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the accuracy
	 */
	public double getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy the accuracy to set
	 */
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the newPlace
	 */
	public Place getNewPlace() {
		return newPlace;
	}

	/**
	 * @param newPlace the newPlace to set
	 */
	public void setNewPlace(Place newPlace) {
		this.newPlace = newPlace;
	}

}
