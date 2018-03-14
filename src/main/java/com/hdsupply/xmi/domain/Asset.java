package com.hdsupply.xmi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Asset {
	
	private int id;
	private String name;
	private int typeId;
	private int subtypeId;
	private AssetLocation location;
	private SensorReading sensors;
	
	private List<String> bluzoneIds;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the subtypeId
	 */
	public int getSubtypeId() {
		return subtypeId;
	}

	/**
	 * @param subtypeId the subtypeId to set
	 */
	public void setSubtypeId(int subtypeId) {
		this.subtypeId = subtypeId;
	}

	/**
	 * @return the location
	 */
	public AssetLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(AssetLocation location) {
		this.location = location;
	}

	/**
	 * @return the sensors
	 */
	public SensorReading getSensors() {
		return sensors;
	}

	/**
	 * @param sensors the sensors to set
	 */
	public void setSensors(SensorReading sensors) {
		this.sensors = sensors;
	}

	/**
	 * @return the bluzoneIds
	 */
	public List<String> getBluzoneIds() {
		return bluzoneIds;
	}

	/**
	 * @param bluzoneIds the bluzoneIds to set
	 */
	public void setBluzoneIds(List<String> bluzoneIds) {
		this.bluzoneIds = bluzoneIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Place getCurrentActivePlace() {
		
		if(this.getLocation() != null && this.getLocation().getPlace() != null && this.getLocation().getMode() != LocationMode.NONE) {
			return this.getLocation().getPlace();
		}
		
		return null;
	}
	
}
