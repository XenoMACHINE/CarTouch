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

	int centerX = 15;
	int centerY = 15;

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

		int radius = 5;
		g.fillCircle(this.centerX - radius, this.centerY - radius, 10);
	}

	public void initalisation(Rectangle bounds) {
		this.centerX = bounds.getWidth() / 2;
		this.centerY = bounds.getHeight() - 15;
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
		// Supprime les obstacles hors ecran
		for (int i = 0; i < this.obstacles.size(); i++) {
			this.obstacles.get(i).increment();
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
			this.centerX = ptr.getX();
			return true;
		}
		return super.handleEvent(event);
	}

}
