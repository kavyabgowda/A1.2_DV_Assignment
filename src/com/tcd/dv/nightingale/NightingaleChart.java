package com.tcd.dv.nightingale;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;

public class NightingaleChart extends PApplet {

	// Variable declaration
	Table nightingaleData;
	ArrayList<Nightingale> firstList = new ArrayList<Nightingale>();
	ArrayList<Nightingale> secondList = new ArrayList<Nightingale>();

	float rotate = 0f;
	float size = 0f;

	@Override
	public void settings() {
		size(1200, 900);

	}

	@Override
	public void setup() {

		// importing nightingale data
		nightingaleData = loadTable("..\\data\\nightingale-data.csv", "header");
		int i = 0;
		for (TableRow r : nightingaleData.rows()) {
			Nightingale n = new Nightingale();
			n.setMonths(r.getString(0));
			n.setAverageSize(r.getInt(1));
			n.setMorDiseases(r.getFloat(5));
			n.setMorWounds(r.getFloat(6));
			n.setMorOthers(r.getFloat(7));

			if (i >= 12)
				secondList.add(n);
			else {
				firstList.add(n);
				i++;
			}
		}

	}

	@Override
	public void draw() {
		background(233, 225, 204);
		
		// Top Label
		textAlign(CENTER);
		textSize(30);
		fill(0, 200);
		text("DIAGRAM of the CAUSES of MORTALITY \n IN THE ARMY IN THE EAST", 600, 75);

		// instruction text
		textAlign(CENTER);
		textSize(14);
		fill(0);
		text("Use Keyboard Left and Right arrows to rotate the chart 1", width - 700 + 40, 2 * height / 3 + 70);

		textAlign(CENTER);
		textSize(12);
		fill(0);
		text("The areas of the Red, Pink and Gray wedges are each measured from the centre  \n as the common vertex. The Red wedges measured from the \n centre of the circle represent area for the deaths from Preventible or \n Mitigable Zymotic diseases; the Gray wedges measured from the centre represent \n the deaths from wounds; & the Pink wedges measured from the centre represent the \n deaths from all other causes. The black lines across the red triangle \n in Nov 1854 marks the boundry of the deaths from all other causes during the month.\n The entire area may be compared by following the Red, pink & the gray\n lines enclosing them.\n",
				350, 500);

		fill(214, 56, 64, 250);
		textAlign(CENTER);
		textSize(13);
		rect(width - 400, 2 * height / 3, 20, 20);
		fill(0);
		text("Death from Zymotic Disease", width - 300 + 25, 2 * height / 3 + 15);

		fill(250, 161, 165, 250);
		rect(width - 400, 2 * height / 3 + 25, 20, 20);
		fill(0);
		text("Death from Other Causes", width - 300 + 25, 2 * height / 3 + 40);

		fill(204, 204, 204, 200);
		rect(width - 400, 2 * height / 3 + 50, 20, 20);
		fill(0);
		text("Death from Wounds", width - 300 + 25, 2 * height / 3 + 65);

		// Text Right
		textAlign(CENTER);
		textSize(16);
		fill(0, 190);
		PFont font;
		font = createFont("algerian", 16);
		textFont(font);
		text("1. APRIL 1854 to MARCH 1855", width - 450 + 75, 2 * height / 12 + 20);

		for (int i = 0; i < firstList.size(); i++) {
			Nightingale r = firstList.get(i);

			// find the Pie values
			float diseaseRadius = sqrt(12 * r.getMorDiseases() / PI) * (8 + size);
			float woundRadius = sqrt(12 * r.getMorWounds() / PI) * (8 + size);
			float otherRadius = sqrt(12 * r.getMorOthers() / PI) * (8 + size);

			// draw pie section--right side
			stroke(2f);
			fill(214, 56, 64, 250);
			arc(width - 400, height / 2, diseaseRadius, diseaseRadius, i * (PI / 6) + rotate,
					i * (PI / 6) + PI / 6 + rotate, PIE);

			fill(250, 161, 165, 250);
			arc(width - 400, height / 2, otherRadius, otherRadius, i * (PI / 6) + rotate,
					i * (PI / 6) + PI / 6 + rotate, PIE);

			fill(204, 204, 204, 200);
			arc(width - 400, height / 2, woundRadius, woundRadius, i * (PI / 6) + rotate,
					i * (PI / 6) + PI / 6 + rotate, PIE);

			String t = r.getMonths();
			String m = t.substring(0, t.indexOf(" "));
			float maxval = max(diseaseRadius, woundRadius, otherRadius);
			float distance = max(150, maxval);

			float leng = 0f;
			for (int j = 0; j < m.length(); j++) {
				char letter = m.charAt(j);
				float letterWith = textWidth(letter);

				leng += letterWith;
				float theta = i * (PI / 6) + rotate + leng / distance;

				pushMatrix();

				translate(width - 400 + (distance + 10) * 0.5f * cos(theta),
						height / 2 + (distance + 10) * 0.5f * sin(theta));
				rotate(theta + PI / 2);

				fill(0);

				textAlign(LEFT, LEFT);
				text(letter, 0, 0);

				popMatrix();
				leng += letterWith;
			}

		}

		// Text left
		textAlign(CENTER);
		textSize(16);
		fill(0, 190);
		text("2. APRIL 1855 to MARCH 1856", 230, 180);

		for (int i = 0; i < secondList.size(); i++) {
			Nightingale r = secondList.get(i);

			// find the Pie values
			float diseaseRadius = sqrt(12 * r.getMorDiseases() / PI) * 8;
			float woundRadius = sqrt(12 * r.getMorWounds() / PI) * 8;
			float otherRadius = sqrt(12 * r.getMorOthers() / PI) * 8;

			// draw pie section--left side
			stroke(2f);
			fill(214, 56, 64, 250);
			arc(width - 1000, height / 2 - 100, diseaseRadius, diseaseRadius, i * (PI / 6), i * (PI / 6) + PI / 6, PIE);

			fill(250, 161, 165, 250);
			arc(width - 1000, height / 2 - 100, otherRadius, otherRadius, i * (PI / 6), i * (PI / 6) + PI / 6, PIE);

			fill(204, 204, 204, 200);
			arc(width - 1000, height / 2 - 100, woundRadius, woundRadius, i * (PI / 6), i * (PI / 6) + PI / 6, PIE);

			String t = r.getMonths();
			String m = t.substring(0, t.indexOf(" "));
			float maxval = max(diseaseRadius, woundRadius, otherRadius);
			float distance = max(150, maxval);

			float leng = 0f;
			for (int j = 0; j < m.length(); j++) {
				char letter = m.charAt(j);
				float letterWidth = textWidth(letter);
				leng += letterWidth;
				float theta = i * PI / 6 + leng / distance;

				pushMatrix();

				translate(width - 1000 + (distance + 10) * 0.5f * cos(theta),
						height / 2 - 100 + (distance + 10) * 0.5f * sin(theta));
				rotate(theta + PI / 2);

				fill(0);

				textAlign(LEFT, LEFT);
				text(letter, 0, 0);

				popMatrix();
				leng += letterWidth;
			}
		}
	}

	@Override
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				size += 0.2f;
				size = size > 2 ? 2 : size;
			} else if (keyCode == DOWN) {
				size -= 0.2f;
				size = size < -2 ? -2 : size;
			} else if (keyCode == LEFT) {
				rotate -= PI / 6;
			} else if (keyCode == RIGHT) {
				rotate += PI / 6;
			}
		}
	}

	public static void main(String[] args) {
		PApplet.main("com.tcd.dv.nightingale.NightingaleChart");
	}

}
