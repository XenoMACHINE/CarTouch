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

	Split container = new Split(true, 0.85f);

	ButtonWrapper backBtn = new ButtonWrapper();

	Widget gameWidget = new GameWidget();

	public PlayPage() {
		// TODO Auto-generated constructor stub

		this.container.setFirst(this.gameWidget);
		this.container.setLast(this.backBtn);

		this.backBtn.setWidget(new Label("X"));
		this.backBtn.addOnClickListener(this);

		setWidget(this.container);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		MyActivity.transition.show(new MainPage(), false);
	}

	// setWidget(this.container);

}
