/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

import java.io.IOException;
import java.util.ArrayList;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.wadapps.storage.Storage;
import ej.widget.StyledWidget;
import moc.lab.models.Obstacle;
import moc.lab.pages.PlayPage;

public class GameWidget extends StyledWidget implements Animation, EventHandler {

	public static enum Level {
		EASY, MEDIUM, HARD
	}

	public static Level level = Level.EASY;
	public static int screenHeight = 0;
	public static int screenWidth = 0;

	public static int playerX = 15;
	public static int playerY = 15;
	public static int playerRadius = 20;
	public static int playerWidth = 0;
	public static int playerHeight = 0;

	public static Image carImage;
	public static Image tankImage;
	public static Image planeImage;

	Image turtleImage;
	Image bloodImage;
	Image background;

	boolean animated = true;
	boolean initialized = false;

	Storage storage = ServiceLoaderFactory.getServiceLoader().getService(Storage.class);

	int timer = 0;

	double increasedPercentage = 0.025;
	double timeInterval = 15;
	int maxTimeInterval = 5; // TODO Change selon lvl

	ArrayList<Obstacle> obstacles = new ArrayList<>();

	public GameWidget() {
		// TODO Auto-generated constructor stub
		PlayPage.score = 0;
		try {
			this.carImage = Image.createImage("/images/car.png");
			this.tankImage = Image.createImage("/images/tank.png");
			this.planeImage = Image.createImage("/images/spaceShip.png");
			this.bloodImage = Image.createImage("/images/hardBlood.png");
			this.background = Image.createImage("/images/background.png");

			this.turtleImage = Image.createImage("/images/normalTurtle.png");

			this.playerRadius = this.turtleImage.getWidth();
			this.playerWidth = this.turtleImage.getWidth();
			this.playerHeight = this.turtleImage.getHeight();

		} catch (IOException e) {
		}
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		g.setColor(Colors.NAVY);

		if (!this.initialized) {
			this.initalisation(g, bounds);
		}

		g.drawImage(this.background, 0, 0, GraphicsContext.LEFT);

		drawObstacles(g);

		// g.fillCircle(this.playerX - (this.playerRadius / 2), this.playerY - (this.playerRadius / 2),
		// this.playerRadius);
		// g.drawRect(this.playerX, this.playerY, this.turtleImage.getWidth(), this.turtleImage.getHeight());

		if (this.animated) {
			g.drawImage(this.turtleImage, this.playerX, this.playerY, GraphicsContext.LEFT);
		} else {
			g.drawImage(this.bloodImage, this.playerX - 15, this.playerY - 25, GraphicsContext.LEFT);
			String key = StorageManager.getInstance().getKey(this.level);
			StorageManager.getInstance().writeScoreInStorage(key);
		}
	}

	public void initalisation(GraphicsContext g, Rectangle bounds) {
		this.playerX = (bounds.getWidth() / 2) - (this.turtleImage.getWidth() / 2);
		this.playerY = bounds.getHeight() - (this.turtleImage.getHeight() + 5);
		this.screenHeight = bounds.getHeight();
		this.screenWidth = bounds.getWidth();
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);

		this.initialized = true;
	}

	public void newObstacles() {
		if (this.timer > this.timeInterval) {
			if (this.timeInterval > this.maxTimeInterval) {
				this.timeInterval -= (this.timeInterval * this.increasedPercentage);
				// System.out.println(this.timeInterval + " ---- " + (this.timeInterval * this.increasedPercentage));
			}

			Obstacle newObstacle = new Obstacle();
			// Évite les superpositions
			int nbAttempts = 0;
			while (this.obstaclesAlreadyAtX(newObstacle) && nbAttempts < 10) { // max 10 tentative, évite infinite loop
				// System.out.println("\ntry again\n");
				nbAttempts++;
				newObstacle.setX((int) (Math.random() * GameWidget.screenHeight));
			}

			if (nbAttempts < 10) {
				this.obstacles.add(newObstacle);
			}
			// System.out.println("\n----------------\n");
			this.timer = 0;
		}
	}

	public boolean obstaclesAlreadyAtX(Obstacle newObstacle) {
		int xLeft = newObstacle.getX();
		int xRight = newObstacle.getX() + newObstacle.getWidth();
		// System.out.println("newObstacle x : " + xLeft + " and " + xRight);

		for (Obstacle obstacle : this.obstacles) {
			// System.out.print(" - " + obstacle.getX());
			// System.out.println(" to " + (obstacle.getX() + obstacle.getWidth()));
			if (xLeft >= obstacle.getX() && xLeft <= (obstacle.getX() + obstacle.getWidth())) {
				return true;
			}
			if (xRight >= obstacle.getX() && xRight <= (obstacle.getX() + obstacle.getWidth())) {
				return true;
			}
		}

		return false;
	}

	public void drawObstacles(GraphicsContext g) {
		for (Obstacle obstacle : this.obstacles) {
			// g.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
			switch (obstacle.getTypeObs()) {
			case CAR:
				g.drawImage(this.carImage, obstacle.getX(), obstacle.getY(), GraphicsContext.LEFT);
				break;
			case TANK:
				g.drawImage(this.tankImage, obstacle.getX(), obstacle.getY(), GraphicsContext.LEFT);
				break;
			case PLANE:
				g.drawImage(this.planeImage, obstacle.getX(), obstacle.getY(), GraphicsContext.LEFT);
				break;

			default:
				g.drawImage(this.carImage, obstacle.getX(), obstacle.getY(), GraphicsContext.LEFT);
				break;
			}
		}
	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(bounds);
		return rect;
	}

	@Override
	public boolean tick(long currentTimeMillis) {

		for (int i = 0; i < this.obstacles.size(); i++) {
			// Fais avancer les obstacles
			this.obstacles.get(i).increment();

			// Gestion des colisions
			if (this.obstacles.get(i).hasColision()) {
				this.animated = false;
			}

			// Supprime les obstacles hors ecran
			if (!this.obstacles.get(i).isAvailable()) {
				this.obstacles.remove(i);
				i--;
			}
		}

		this.timer += 1;
		PlayPage.score += 1;
		this.newObstacles();

		repaint();
		return this.animated;
	}

	@Override
	public boolean handleEvent(int event) {
		// TODO Auto-generated method stub
		if (Event.getType(event) == Event.POINTER) {
			Pointer ptr = (Pointer) Event.getGenerator(event);
			int touchLimit = this.turtleImage.getWidth() + 10;
			if (ptr.getX() - this.playerX < touchLimit && ptr.getX() - this.playerX > -touchLimit) {
				this.playerX = ptr.getX() - (this.turtleImage.getWidth() / 2);
				if (ptr.getY() > screenHeight / 2) {
					this.playerY = ptr.getY() - 50;
				}
			}
			return true;
		}
		return super.handleEvent(event);
	}

}
