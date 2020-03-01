package com.tcd.dv.minard;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;

public class MinardChart extends PApplet {

	ArrayList<Cities> cities = new ArrayList<Cities>();
	ArrayList<Survivers> survivers = new ArrayList<Survivers>();
	ArrayList<Temperature> temperatures = new ArrayList<Temperature>();
	Table minardData;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("com.tcd.dv.minard.MinardChart");
	}

	@Override
	public void settings() {
		// TODO Auto-generated method stub
		size(1300, 1000);
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		background(255);
		minardData = loadTable("..\\data\\minard-data.csv", "header");

		for (TableRow r : minardData.rows()) {
			if (!Double.isNaN(r.getFloat(0))) {
				Cities city = new Cities();
				city.setLongitudeC(r.getFloat(0));
				city.setLatitudeC(r.getFloat(1));
				city.setCity(r.getString(2));
				cities.add(city);
			}
			if (!Double.isNaN(r.getFloat(3))) {
				Temperature tp = new Temperature();
				tp.setLongitudeT(r.getFloat(3));
				tp.setTemperature(r.getInt(4));
				tp.setNumberOfDays(r.getInt(5));
				tp.setMonth(r.getString(6));
				tp.setDay(r.getInt(7));
				temperatures.add(tp);
			}

			Survivers surviver = new Survivers();
			surviver.setLongitudeP(r.getFloat(8));
			surviver.setLatitudeP(r.getFloat(9));
			surviver.setSurviver(r.getInt(10));
			surviver.setDirection(r.getString(11));
			surviver.setDivision(r.getInt(12));
			survivers.add(surviver);
		}

	}

	@Override
	public void draw() {
		background(252, 240, 145);
		// TODO Auto-generated method stub
		fill(0);
		// text(mouseX + " " + mouseY, 100, 100);

		int[] clr = { color(51, 115, 158), color(246, 92, 120), color(248, 180, 0), color(120, 74, 118),
				color(74, 120, 77), color(32, 32, 32) };

		float x1, y1, x2, y2;
		for (int i = survivers.size() - 2; i >= 1; i--) {
			stroke(clr[0]);
			Survivers s = survivers.get(i);
			if (s.getDivision() != survivers.get(i + 1).getDivision())
				continue;
			float strokeW = norm(s.getSurviver(), 4000, 340000) * 40;
			strokeWeight(max(1, strokeW));
			float alpha = max(200, norm(s.getSurviver(), 4000, 340000) * 255);
			if (s.getDivision() == 1 && s.getDirection().equals("A")) {
				stroke(clr[0]);
			} else if (s.getDivision() == 1 && s.getDirection().equals("R")) {
				stroke(clr[1]);
			} else if (s.getDivision() == 2 && s.getDirection().equals("A")) {
				stroke(clr[2]);
			} else if (s.getDivision() == 2 && s.getDirection().equals("R")) {
				stroke(clr[3]);
			} else if (s.getDivision() == 3 && s.getDirection().equals("A")) {
				stroke(clr[4]);
			} else if (s.getDivision() == 3 && s.getDirection().equals("R")) {
				stroke(clr[5]);
			}
			x1 = s.getLongitudeP();
			y1 = s.getLatitudeP();
			x2 = survivers.get(i + 1).getLongitudeP();
			y2 = survivers.get(i + 1).getLatitudeP();
			float x1norm = map(x1, 24, (float) 37.6, width - 1100, width - 100);

			float x2norm = map(x2, 24, (float) 37.6, width - 1100, width - 100);
			float y1norm = map(y1, (float) 54.2, (float) 55.8, height / 3, height / 8);
			float y2norm = map(y2, (float) 54.2, (float) 55.8, height / 3, height / 8);
			line(x1norm, y1norm, x2norm, y2norm);
		}

		Survivers s = survivers.get(0);
		float strokeW = norm(s.getSurviver(), 4000, 340000) * 50;
		strokeWeight(max(1, strokeW));
		float alpha = max(200, norm(s.getSurviver(), 4000, 340000) * 255);

		x1 = s.getLongitudeP();
		y1 = s.getLatitudeP();
		x2 = survivers.get(1).getLongitudeP();
		y2 = survivers.get(1).getLatitudeP();
		float x1norm = map(x1, 24, (float) 37.6, width - 1100, width - 100);

		float x2norm = map(x2, 24, (float) 37.6, width - 1100, width - 100);
		float y1norm = map(y1, (float) 54.2, (float) 55.8, height / 3, height / 8);
		float y2norm = map(y2, (float) 54.2, (float) 55.8, height / 3, height / 8);
		line(x1norm, y1norm, x2norm, y2norm);

		for (int i = 0; i < survivers.size() - 1; i++) {
			float y = survivers.get(i).getLatitudeP();
			float x = survivers.get(i).getLongitudeP();
			int temp = survivers.get(i).getSurviver();
			int temp1 = survivers.get(i + 1).getSurviver();
			if (temp == temp1)
				continue;
			x1norm = map(x, 24, (float) 37.6, width - 1100, width - 100);
			y1norm = map(y, (float) 54.2, (float) 55.8, height / 3, height / 8);
			fill(0);
			textSize(9);
			text(survivers.get(i).getSurviver(), x1norm - 20, y1norm + 30);
		}
		textSize(12);

		for (int i = 0; i < 6; i++) {
			fill(0);
			strokeWeight(2);
			stroke(252, 249, 234);
			line(width - 1100, i * 6 * 5 + 450, width - 100, i * 6 * 5 + 450);
			strokeWeight(2);
			stroke(0);
			textAlign(RIGHT);
		}

		text("Temperature(Celsius)", 200, 530);
		text("Longitude", 650, 620);

		fill(0);
		strokeWeight(2);
		stroke(252, 249, 234);
		line(220, 435, 220, 180 + 450);

		int i = 0;
		do {
			Temperature temp1 = temperatures.get(i + 1);
			Temperature temp = temperatures.get(i);

			float tx1 = temp.getLongitudeT();
			float ty1 = -temp.getTemperature() * 5 + 450;
			float tx2 = temp1.getLongitudeT();
			float ty2 = -temp1.getTemperature() * 5 + 450;

			fill(0);
			strokeWeight(2);
			stroke(105, 100, 100);
			x1norm = map(tx1, 24, (float) 37.6, width - 1100, width - 100);
			x2norm = map(tx2, 24, (float) 37.6, width - 1100, width - 100);
			y1norm = map(ty1, (float) 54.2, (float) 55.8, height / 3, height / 8);
			y2norm = map(ty2, (float) 54.2, (float) 55.8, height / 3, height / 8);
			line(x1norm, ty1, x2norm, ty2);

			// Draw Point
			fill(247, 95, 0);
			strokeWeight(2);
			stroke(247, 95, 0);
			ellipse(x1norm, ty1, 5, 5);
			fill(0);
			text(temp.getTemperature(), x1norm + 10, ty1 + 20);
			i++;
		} while (i < temperatures.size() - 1);

		// Initial Temperature point
		Temperature temp = temperatures.get(temperatures.size() - 1);

		float tx1 = temp.getLongitudeT();
		float ty1 = -temp.getTemperature() * 5 + 450;

		fill(0);
		strokeWeight(2);
		stroke(0);
		x1norm = map(tx1, 24, (float) 37.6, width - 1100, width - 100);
		y1norm = map(ty1, (float) 54.2, (float) 55.8, height / 3, height / 8);
		ellipse(x1norm, ty1, 5, 5);

		// Display city Names
		for (i = 0; i < cities.size(); i++) {
			fill(0);
			stroke(0);
			strokeWeight(2);
			float cy = cities.get(i).getLatitudeC();
			float cx = cities.get(i).getLongitudeC();
			x1norm = map(cx, 24, (float) 37.6, width - 1100, width - 100);
			y1norm = map(cy, (float) 54.2, (float) 55.8, height / 3, height / 8);

			ellipse(x1norm, y1norm, 5, 5);
			line(x1norm, y1norm, x1norm, y1norm + 10);
			text(cities.get(i).getCity(), x1norm + 20, y1norm + 20);
		}

		// Display Label and heading

		// Top Label
		textAlign(CENTER);
		PFont font;
		font = createFont("constantia", 16);
		textFont(font);
		textSize(30);
		fill(0, 200);
		text("Charles Joseph Minard's Map of Napolean's Russia Campaign", 700, 40);
		textSize(14);

		// Labels
		stroke(clr[0]);
		fill(clr[0]);
		rect(30, 150, 10, 10);
		text("Division 1 Troop Advance", 130, 160);

		stroke(clr[1]);
		fill(clr[1]);
		rect(30, 180, 10, 10);
		text("Division 1 Troop Retreat", 125, 190);

		stroke(clr[2]);
		fill(clr[2]);
		rect(30, 210, 10, 10);
		text("Division 2 Troop \n Advance", 105, 220);

		stroke(clr[3]);
		fill(clr[3]);
		rect(30, 250, 10, 10);
		text("Division 2 Troop \n Retreat", 105, 260);

		stroke(clr[4]);
		fill(clr[4]);
		rect(30, 290, 10, 10);
		text("Division 3 Troop \n Advance", 105, 300);

		stroke(clr[5]);
		fill(clr[5]);
		rect(30, 330, 10, 10);
		text("Division 3 Troop \n Retreat", 105, 340);

	}
}
