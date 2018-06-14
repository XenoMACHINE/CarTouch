/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

import java.util.ArrayList;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.widget.StyledWidget;
import moc.lab.models.Obstacle;

/**
 *
 */
public class GameWidget extends StyledWidget implements Animation, EventHandler {

	public static int screenHeight = 0;
	public static int screenWidth = 0;

	public static int playerX = 15;
	public static int playerY = 15;
	public static int playerRadius = 12;

	boolean animated = true;
	boolean initialized = false;

	int timer = 0;

	ArrayList<Obstacle> obstacles = new ArrayList<>();

	public GameWidget() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		g.setColor(Colors.NAVY);

		if (!this.initialized) {
			this.initalisation(bounds);
		}

		drawObstacles(g);

		g.fillCircle(this.playerX - (this.playerRadius / 2), this.playerY - (this.playerRadius / 2), this.playerRadius);
	}

	public void initalisation(Rectangle bounds) {
		this.playerX = bounds.getWidth() / 2;
		this.playerY = bounds.getHeight() - 15;
		this.screenHeight = bounds.getHeight();
		this.screenWidth = bounds.getWidth();
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);

		this.initialized = true;
	}

	public void newObstacles() {
		// TODO Changer selon niveaux
		if (this.obstacles.size() < 3 && this.timer > 20) {
			this.obstacles.add(new Obstacle());

			this.timer = 0;
		}
	}

	public void drawObstacles(GraphicsContext g) {
		for (Obstacle obstacle : this.obstacles) {
			g.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
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
		this.newObstacles();

		repaint();
		return this.animated;
	}

	@Override
	public boolean handleEvent(int event) {
		// TODO Auto-generated method stub
		if (Event.getType(event) == Event.POINTER) {
			Pointer ptr = (Pointer) Event.getGenerator(event);
			this.playerX = ptr.getX();
			return true;
		}
		return super.handleEvent(event);
	}

}
