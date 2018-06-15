/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab.pages;

import java.util.ArrayList;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import moc.lab.MyActivity;
import moc.lab.StorageManager;

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
	ArrayList<Integer> scoresIntArray = new ArrayList<>();

	public ScorePage() {

		this.containerScore = new Split(false, (float) 0.2);
		this.containerTitleScore = new Split(true, (float) 0.8);

		list = new List(false);

		String scores = StorageManager.getInstance().getScores();

		String tmp = "";
		for (char c : scores.toCharArray()) {
			if (c != ',') {
				tmp += c;
			} else {
				Integer intScore = new Integer(Integer.parseInt(tmp));
				if (!this.scoresIntArray.contains(intScore)) {
					this.scoresIntArray.add(intScore);
				}
				tmp = "";
			}
		}

		Integer intScore = new Integer(Integer.parseInt(tmp));
		if (!this.scoresIntArray.contains(intScore)) {
			this.scoresIntArray.add(intScore);
		}

		this.sortArray();

		int max = this.scoresIntArray.size();
		if (max > 10) {
			max = 10;
		}
		for (int i = 0; i < max; i++) {
			// for (Integer score : scoresIntArray) {
			Label lbl = new Label("" + this.scoresIntArray.get(i));
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

	public void sortArray() {
		ArrayList<Integer> sortArray = new ArrayList<>();

		while (this.scoresIntArray.size() > 0) {
			Integer max = new Integer(0);
			int index = 0;
			for (int i = 0; i < this.scoresIntArray.size(); i++) {
				if (this.scoresIntArray.get(i).intValue() > max.intValue()) {
					max = this.scoresIntArray.get(i);
					index = i;
				}
			}

			sortArray.add(max);
			this.scoresIntArray.remove(index);
		}

		this.scoresIntArray = sortArray;
	}

	public void printArray(ArrayList<Integer> array) {
		for (Integer integer : array) {
			System.out.print(integer + ", ");
		}
	}

}
