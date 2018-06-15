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

	public MainPage() {

		this.container = new Split(false, (float) 0.2);
		this.containerTitle = new Split(true, (float) 0.8);

		Label title = new Label("CarTouch");
		title.addClassSelector("TITLE");

		ButtonWrapper play = new ButtonWrapper();
		play.addClassSelector("BTNBLUE");
		Label titlePlay = new Label("JOUER");
		titlePlay.addClassSelector("LABELBLUE");
		play.setWidget(titlePlay);

		ButtonWrapper score = new ButtonWrapper();
		score.addClassSelector("BTNBLUE");
		Label titleScore = new Label("SCORE");
		titleScore.addClassSelector("LABELBLUE");
		score.setWidget(titleScore);

		ButtonWrapper settings = new ButtonWrapper();
		settings.addClassSelector("BTNBLUE");
		Label titleSettings = new Label("SETTINGS");
		titleSettings.addClassSelector("LABELBLUE");
		settings.setWidget(titleSettings);

		ButtonWrapper exit = new ButtonWrapper();
		exit.addClassSelector("BTNBARRED");
		Label titleExit = new Label("X");
		titleExit.addClassSelector("LABELBARRED");
		exit.setWidget(titleExit);

		play.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new PlayPage(), true);
			}
		});

		score.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new ScorePage(), true);
			}
		});

		settings.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				MyActivity.transition.show(new SettingsPage(), true);
			}
		});

		exit.addOnClickListener(new OnClickListener() {

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

		this.Btnlist.add(play);
		this.Btnlist.add(score);
		this.Btnlist.add(settings);
		this.containerTitle.setFirst(title);
		this.containerTitle.setLast(exit);
		this.container.setFirst(this.containerTitle);
		this.container.setLast(this.Btnlist);
		setWidget(this.container);

	}

}
