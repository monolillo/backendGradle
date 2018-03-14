package com.hdsupply.xmi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A location.
 * A geographical location that encloses Assets and Places.
 * For example: Mooresville Office, KINSTON Plant, etc
 * 
 * @author Julian F. Nunez <julian.nunez@neoris.com>
 * @created Jan 10, 2018
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {

	private String id;
	private String name;
	private String image;
	private String imageUrl;
	private boolean active;
	
	private double xmax;
	private double xmin;
	private double ymax;
	private double ymin;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the xmax
	 */
	public double getXmax() {
		return xmax;
	}

	/**
	 * @param xmax the xmax to set
	 */
	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	/**
	 * @return the xmin
	 */
	public double getXmin() {
		return xmin;
	}

	/**
	 * @param xmin the xmin to set
	 */
	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	/**
	 * @return the ymax
	 */
	public double getYmax() {
		return ymax;
	}

	/**
	 * @param ymax the ymax to set
	 */
	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	/**
	 * @return the ymin
	 */
	public double getYmin() {
		return ymin;
	}

	/**
	 * @param ymin the ymin to set
	 */
	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
	
}
