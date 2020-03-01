package com.tcd.dv.minard;

public class Temperature {
	private float longitudeT = 0;
	private int temperature = 0;
	private int numberOfDays = 0;
	private String month = "";
	private int day = 0;
	public float getLongitudeT() {
		return longitudeT;
	}
	public void setLongitudeT(float longitudeT) {
		this.longitudeT = longitudeT;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}
