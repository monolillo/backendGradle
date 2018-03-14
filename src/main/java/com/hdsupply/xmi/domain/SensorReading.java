package com.hdsupply.xmi.domain;

public class SensorReading {
	
	private float temp;
	private double totalVibration;
	private double pkX;
	private double pkY;
	private double pkZ;
	private double pkpkX;
	private double pkpkY;
	private double pkpkZ;
	private boolean running;
	
	/**
	 * @return the temp
	 */
	public float getTemp() {
		return temp;
	}
	
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(float temp) {
		this.temp = temp;
	}
	
	/**
	 * @return the totalVibration
	 */
	public double getTotalVibration() {
		return totalVibration;
	}
	
	/**
	 * @param totalVibration the totalVibration to set
	 */
	public void setTotalVibration(double totalVibration) {
		this.totalVibration = totalVibration;
	}
	
	/**
	 * @return the pkX
	 */
	public double getPkX() {
		return pkX;
	}
	
	/**
	 * @param pkX the pkX to set
	 */
	public void setPkX(double pkX) {
		this.pkX = pkX;
	}
	
	/**
	 * @return the pkY
	 */
	public double getPkY() {
		return pkY;
	}
	
	/**
	 * @param pkY the pkY to set
	 */
	public void setPkY(double pkY) {
		this.pkY = pkY;
	}
	
	/**
	 * @return the pkZ
	 */
	public double getPkZ() {
		return pkZ;
	}
	
	/**
	 * @param pkZ the pkZ to set
	 */
	public void setPkZ(double pkZ) {
		this.pkZ = pkZ;
	}
	
	/**
	 * @return the pkpkX
	 */
	public double getPkpkX() {
		return pkpkX;
	}
	
	/**
	 * @param pkpkX the pkpkX to set
	 */
	public void setPkpkX(double pkpkX) {
		this.pkpkX = pkpkX;
	}
	
	/**
	 * @return the pkpkY
	 */
	public double getPkpkY() {
		return pkpkY;
	}
	
	/**
	 * @param pkpkY the pkpkY to set
	 */
	public void setPkpkY(double pkpkY) {
		this.pkpkY = pkpkY;
	}
	
	/**
	 * @return the pkpkZ
	 */
	public double getPkpkZ() {
		return pkpkZ;
	}
	
	/**
	 * @param pkpkZ the pkpkZ to set
	 */
	public void setPkpkZ(double pkpkZ) {
		this.pkpkZ = pkpkZ;
	}
	
	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

}
