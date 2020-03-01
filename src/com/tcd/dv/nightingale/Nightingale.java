package com.tcd.dv.nightingale;

public class Nightingale 
{
	  private String months;
	  public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public int getAverageSize() {
		return averageSize;
	}
	public void setAverageSize(int averageSize) {
		this.averageSize = averageSize;
	}
	public float getMorDiseases() {
		return morDiseases;
	}
	public void setMorDiseases(float morDiseases) {
		this.morDiseases = morDiseases;
	}
	public float getMorWounds() {
		return morWounds;
	}
	public void setMorWounds(float morWounds) {
		this.morWounds = morWounds;
	}
	public float getMorOthers() {
		return morOthers;
	}
	public void setMorOthers(float morOthers) {
		this.morOthers = morOthers;
	}
	public int averageSize; 
	  public float morDiseases;
	  public float morWounds;
	  public float morOthers;
}
