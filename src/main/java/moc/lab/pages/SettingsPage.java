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
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.MyActivity;

/**
 *
 */
public class SettingsPage extends Page {

	private static Split containerSettings;
	private static Split containerTitleSettings;

	List Btnlist = new List(false);

	public SettingsPage() {

		this.containerSettings = new Split(false, (float) 0.2);
		this.containerTitleSettings = new Split(true, (float) 0.8);

		Label settingsTitle = new Label("Settings");
		settingsTitle.addClassSelector("TITLE");

		ButtonWrapper easyMode = new ButtonWrapper();
		easyMode.addClassSelector("BTNGREEN");
		Label titleEasyMode = new Label("EASY");
		titleEasyMode.addClassSelector("LABELGREEN");
		easyMode.setWidget(titleEasyMode);

		ButtonWrapper normalMode = new ButtonWrapper();
		normalMode.addClassSelector("BTNBLUE");
		Label titleNormalMode = new Label("NORMAL");
		titleNormalMode.addClassSelector("LABELBLUE");
		normalMode.setWidget(titleNormalMode);

		ButtonWrapper hardMode = new ButtonWrapper();
		hardMode.addClassSelector("BTNRED");
		Label titleHardMode = new Label("HARD");
		titleHardMode.addClassSelector("LABELRED");
		hardMode.setWidget(titleHardMode);

		ButtonWrapper back = new ButtonWrapper();
		back.addClassSelector("BTNBAR");
		Label titleBack = new Label("<-");
		titleBack.addClassSelector("LABELBAR");
		back.setWidget(titleBack);

		back.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MyActivity.transition.show(new MainPage(), false);
			}
		});

		this.Btnlist.add(easyMode);
		this.Btnlist.add(normalMode);
		this.Btnlist.add(hardMode);
		this.containerTitleSettings.setFirst(settingsTitle);
		this.containerTitleSettings.setLast(back);
		this.containerSettings.setFirst(this.containerTitleSettings);
		this.containerSettings.setLast(this.Btnlist);
		setWidget(this.containerSettings);

	}
}
