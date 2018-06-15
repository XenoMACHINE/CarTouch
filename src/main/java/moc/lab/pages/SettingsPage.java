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

	Label settingsTitle = new Label("Settings");
	Label titleEasyMode = new Label("EASY");
	Label titleNormalMode = new Label("NORMAL");
	Label titleHardMode = new Label("HARD");
	Label titleBack = new Label("X");

	ButtonWrapper easyMode = new ButtonWrapper();
	ButtonWrapper normalMode = new ButtonWrapper();
	ButtonWrapper hardMode = new ButtonWrapper();
	ButtonWrapper back = new ButtonWrapper();

	public SettingsPage() {

		this.containerSettings = new Split(false, (float) 0.2);
		this.containerTitleSettings = new Split(true, (float) 0.8);

		this.settingsTitle.addClassSelector("TITLE");
		this.titleEasyMode.addClassSelector("LABELGREEN");
		this.titleNormalMode.addClassSelector("LABELORANGE");
		this.titleHardMode.addClassSelector("LABELRED");
		this.titleBack.addClassSelector("LABELBAR");

		this.easyMode.addClassSelector("BTNGREEN");
		this.easyMode.setWidget(this.titleEasyMode);

		this.normalMode.addClassSelector("BTNORANGE");
		this.normalMode.setWidget(this.titleNormalMode);

		this.hardMode.addClassSelector("BTNRED");
		this.hardMode.setWidget(this.titleHardMode);

		this.back.addClassSelector("BTNBAR");
		this.back.setWidget(this.titleBack);

		this.back.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				MyActivity.transition.show(new MainPage(), false);
			}
		});

		this.Btnlist.add(this.easyMode);
		this.Btnlist.add(this.normalMode);
		this.Btnlist.add(this.hardMode);
		this.containerTitleSettings.setFirst(this.settingsTitle);
		this.containerTitleSettings.setLast(this.back);
		this.containerSettings.setFirst(this.containerTitleSettings);
		this.containerSettings.setLast(this.Btnlist);
		setWidget(this.containerSettings);

	}
}
