/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.models;

import moc.lab.GameWidget;

/**
 *
 */
public class Obstacle {

	int x, y;
	int width = 30;
	int height = 55;
	int speed = 5;

	boolean available = true; // is on screen or not

	/*
	 * public Obstacle() { this.x = 100; this.y = -(this.height + 20); }
	 */

	public Obstacle() {
		this.x = (int) (Math.random() * GameWidget.screenHeight);
		this.y = -(this.height);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void increment() {
		if (this.available) {
			this.y += this.speed;
			if (this.y >= GameWidget.screenHeight) {
				this.available = false;
			}
		}
	}

	public boolean hasColision() {
		int x = GameWidget.playerX - (GameWidget.playerRadius / 2);
		int y = GameWidget.playerY - (GameWidget.playerRadius / 2);
		if (x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height)) {
			return true;
		}

		x = GameWidget.playerX + (GameWidget.playerRadius / 2);
		y = GameWidget.playerY + (GameWidget.playerRadius / 2);
		if (x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height)) {
			return true;
		}

		return false;
	}

}
