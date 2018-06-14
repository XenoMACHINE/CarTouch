/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

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

/**
 *
 */
public class GameWidget extends StyledWidget implements Animation, EventHandler {

	int centerX = 15;
	int centerY = 15;

	boolean animated = true;
	boolean initialized = false;

	public GameWidget() {
		// TODO Auto-generated constructor stub
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		g.setColor(Colors.NAVY);
		int radius = 5;

		if (!this.initialized) {
			this.centerX = bounds.getWidth() / 2;
			this.centerY = bounds.getHeight() - 15;
			this.initialized = true;
		}

		g.drawRect(15, 100, 40, 100);

		g.fillCircle(this.centerX - radius, this.centerY - radius, 10);
	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(bounds);
		return rect;
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		// TODO Auto-generated method stub
		repaint();
		// this.centerX += 1;
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
