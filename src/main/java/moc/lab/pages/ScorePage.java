/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.pages;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.MyActivity;

/**
 *
 */
public class ScorePage extends Page {

	private static Scroll scroll;
	private static List list;

	private final Split containerScore;
	private final Split containerTitleScore;

	Label scoreTitle = new Label("Scores");
	Label titleBack = new Label("X");

	ButtonWrapper back = new ButtonWrapper();

	public ScorePage() {

		this.containerScore = new Split(false, (float) 0.2);
		this.containerTitleScore = new Split(true, (float) 0.8);

		list = new List(false);

		for (int i = 1; i <= 20; i++) {
			Label lbl = new Label(String.valueOf(i));
			lbl.addClassSelector("LABELBLUE");
			ButtonWrapper btnList = new ButtonWrapper();
			btnList.addClassSelector("BTNBLUE");
			btnList.setWidget(lbl);
			list.add(btnList);
		}

		this.scoreTitle.addClassSelector("TITLE");
		this.titleBack.addClassSelector("LABELBAR");
		this.back.addClassSelector("BTNBAR");
		this.back.setWidget(this.titleBack);

		this.back.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				MyActivity.transition.show(new MainPage(), false);
			}
		});

		scroll = new Scroll(false, true);
		scroll.setWidget(list);

		this.containerTitleScore.setFirst(this.scoreTitle);
		this.containerTitleScore.setLast(this.back);
		this.containerScore.setFirst(this.containerTitleScore);
		this.containerScore.setLast(scroll);
		setWidget(this.containerScore);

	}

}
