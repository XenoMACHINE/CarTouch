/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.pages;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.MyActivity;

public class MainPage extends Page {

	private final Split container;
	private final Split containerTitle;

	List Btnlist = new List(false);

	Label mainTitle = new Label("CarTouch");
	Label titlePlay = new Label("JOUER");
	Label titleScore = new Label("SCORE");
	Label titleSettings = new Label("SETTINGS");
	Label titleExit = new Label("X");

	ButtonWrapper play = new ButtonWrapper();
	ButtonWrapper score = new ButtonWrapper();
	ButtonWrapper settings = new ButtonWrapper();
	ButtonWrapper exit = new ButtonWrapper();

	public MainPage() {

		this.container = new Split(false, (float) 0.2);
		this.containerTitle = new Split(true, (float) 0.8);

		this.mainTitle.addClassSelector("TITLE");
		this.titlePlay.addClassSelector("LABELBLUE");
		this.titleScore.addClassSelector("LABELBLUE");
		this.titleSettings.addClassSelector("LABELBLUE");
		this.titleExit.addClassSelector("LABELBAR");

		this.play.addClassSelector("BTNBLUE");
		this.play.setWidget(this.titlePlay);

		this.score.addClassSelector("BTNBLUE");
		this.score.setWidget(this.titleScore);

		this.settings.addClassSelector("BTNBLUE");
		this.settings.setWidget(this.titleSettings);

		this.exit.addClassSelector("BTNBAR");
		this.exit.setWidget(this.titleExit);

		this.play.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new PlayPage(), true);
			}
		});

		this.score.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new ScorePage(), true);
			}
		});

		this.settings.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new SettingsPage(), true);
			}
		});

		this.exit.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
				if (exitHandler != null) {
					exitHandler.exit();
					System.out.println("Exit");
				}
			}
		});

		this.Btnlist.add(this.play);
		this.Btnlist.add(this.score);
		this.Btnlist.add(this.settings);
		this.containerTitle.setFirst(this.mainTitle);
		this.containerTitle.setLast(this.exit);
		this.container.setFirst(this.containerTitle);
		this.container.setLast(this.Btnlist);
		setWidget(this.container);

	}

}
