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

	public static enum TypeObs {
		CAR, PLANE, TANK
	}

	int x, y;
	int width = 30;
	int height = 55;

	int speed = (int) (Math.random() * 13) + 5;
	boolean available = true; // is on screen or not

	TypeObs typeObs = TypeObs.CAR;

	public Obstacle() {
		this.x = (int) (Math.random() * (GameWidget.screenWidth - this.width));
		this.y = -(this.height);

		int typeCarPercentage = (int) (Math.random() * 100);

		if (typeCarPercentage < 15) {
			this.typeObs = TypeObs.PLANE;
			this.width = GameWidget.planeImage.getWidth();
			this.height = GameWidget.planeImage.getHeight();
			this.speed = 15;
		} else if (typeCarPercentage < 35) {
			this.typeObs = TypeObs.TANK;
			this.width = GameWidget.tankImage.getWidth();
			this.height = GameWidget.tankImage.getHeight();
		} else {
			this.typeObs = TypeObs.CAR;
			this.width = GameWidget.carImage.getWidth();
			this.height = GameWidget.carImage.getHeight();
		}
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

	public TypeObs getTypeObs() {
		return this.typeObs;
	}

	public void setTypeObs(TypeObs typeObs) {
		this.typeObs = typeObs;
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

	@Override
	public String toString() {
		return "Obstacle [x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height
				+ ", available=" + this.available + "]";
	}

}
