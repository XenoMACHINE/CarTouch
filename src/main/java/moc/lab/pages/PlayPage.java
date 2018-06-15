/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.pages;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.mwt.Widget;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.GameWidget;
import moc.lab.MyActivity;

/**
 *
 */
public class PlayPage extends Page implements OnClickListener, Animation {

	public static int score = 0;
	Split container = new Split(false, 0.2f);
	Split containerTitle = new Split(true, 0.8f);

	Label playTitle = new Label("Score : " + score);
	Label titleBackBtn = new Label("X");

	ButtonWrapper backBtn = new ButtonWrapper();

	Widget gameWidget = new GameWidget();

	public PlayPage() {
		// TODO Auto-generated constructor stub
		ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);

		this.playTitle.addClassSelector("TITLE");
		this.titleBackBtn.addClassSelector("LABELBAR");

		this.backBtn.addClassSelector("BTNBAR");
		this.backBtn.setWidget(this.titleBackBtn);
		this.backBtn.addOnClickListener(this);

		this.containerTitle.setFirst(this.playTitle);
		this.containerTitle.setLast(this.backBtn);
		this.container.setFirst(this.containerTitle);
		this.container.setLast(this.gameWidget);

		setWidget(this.container);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		MyActivity.transition.show(new MainPage(), false);
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		// TODO Auto-generated method stub
		this.playTitle.setText("Score : " + score);
		return true;
	}

	// setWidget(this.container);
}
