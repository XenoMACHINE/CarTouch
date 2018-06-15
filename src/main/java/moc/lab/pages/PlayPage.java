/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.pages;

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
public class PlayPage extends Page implements OnClickListener {

	Split container = new Split(false, 0.2f);
	Split containerTitle = new Split(true, 0.8f);

	Label playTitle = new Label("Score  :  0");
	Label titleBackBtn = new Label("X");

	ButtonWrapper backBtn = new ButtonWrapper();

	Widget gameWidget = new GameWidget();

	public PlayPage() {
		// TODO Auto-generated constructor stub

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

	// setWidget(this.container);

}
