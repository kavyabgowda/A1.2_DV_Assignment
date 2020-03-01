package com.tcd.dv.minard;

public class Survivers {
	private float longitudeP = 0;
	private float latitudeP = 0;
	private int surviver = 0;
	public float getLongitudeP() {
		return longitudeP;
	}
	public void setLongitudeP(float longitudeP) {
		this.longitudeP = longitudeP;
	}
	public float getLatitudeP() {
		return latitudeP;
	}
	public void setLatitudeP(float latitudeP) {
		this.latitudeP = latitudeP;
	}
	public int getSurviver() {
		return surviver;
	}
	public void setSurviver(int surviver) {
		this.surviver = surviver;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	private String direction = "";
	private int division = 0;

}
