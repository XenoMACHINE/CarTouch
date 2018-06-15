/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.MWT;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.background.SimpleRoundedPlainBackground;
import ej.style.border.ComplexRectangularBorder;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.container.transition.SlideScreenshotTransitionContainer;
import ej.widget.container.transition.TransitionContainer;
import moc.lab.pages.MainPage;

/**
 *
 */
public class MyActivity implements Activity {

	public static TransitionContainer transition;

	private void InitializeStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();

		// BUTTON Style based on Label & Button classes
		SimpleOutline btnMargin = new SimpleOutline(7);
		SimpleRoundedPlainBackground backgroundButtons = new SimpleRoundedPlainBackground(20);
		SimpleRoundedPlainBackground backgroundButtonsBar = new SimpleRoundedPlainBackground(0);

		// title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setForegroundColor(Colors.WHITE);
		titleStyle.setBackgroundColor(Colors.BLACK);
		ComplexRectangularBorder titleBorder = new ComplexRectangularBorder();
		titleStyle.setBorder(titleBorder);
		titleStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// blue button
		EditableStyle btnBlueStyle = new EditableStyle();
		btnBlueStyle.setMargin(btnMargin);
		btnBlueStyle.setPadding(btnMargin);
		btnBlueStyle.setBackground(backgroundButtons);
		btnBlueStyle.setBackgroundColor(Colors.NAVY);
		btnBlueStyle.setForegroundColor(Colors.WHITE);
		btnBlueStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// green button
		EditableStyle btnGreenStyle = new EditableStyle();
		btnGreenStyle.setMargin(btnMargin);
		btnGreenStyle.setPadding(btnMargin);
		btnGreenStyle.setBackground(backgroundButtons);
		btnGreenStyle.setBackgroundColor(Colors.GREEN);
		btnGreenStyle.setForegroundColor(Colors.WHITE);
		btnGreenStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// yellow button
		EditableStyle btnYellowStyle = new EditableStyle();
		btnYellowStyle.setMargin(btnMargin);
		btnYellowStyle.setPadding(btnMargin);
		btnYellowStyle.setBackground(backgroundButtons);
		btnYellowStyle.setBackgroundColor(Colors.PURPLE);
		btnYellowStyle.setForegroundColor(Colors.WHITE);
		btnYellowStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// red button
		EditableStyle btnRedStyle = new EditableStyle();
		btnRedStyle.setMargin(btnMargin);
		btnRedStyle.setPadding(btnMargin);
		btnRedStyle.setBackground(backgroundButtons);
		btnRedStyle.setBackgroundColor(Colors.RED);
		btnRedStyle.setForegroundColor(Colors.WHITE);
		btnRedStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// navigation bar button
		EditableStyle btnBarStyle = new EditableStyle();
		btnBarStyle.setBackgroundColor(Colors.BLACK);
		btnBarStyle.setForegroundColor(Colors.WHITE);
		btnBarStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// navigation bar button red
		EditableStyle btnBarRedStyle = new EditableStyle();
		btnBarRedStyle.setBackgroundColor(Colors.RED);
		btnBarRedStyle.setForegroundColor(Colors.WHITE);
		btnBarRedStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// selectors

		// title
		ClassSelector titleSelector = new ClassSelector("TITLE");

		// blue
		ClassSelector btnBlueSelector = new ClassSelector("BTNBLUE");
		ClassSelector labelBlueSelector = new ClassSelector("LABELBLUE");
		ChildCombinator parentsBlue = new ChildCombinator(btnBlueSelector, labelBlueSelector);

		// green
		ClassSelector btnGreenSelector = new ClassSelector("BTNGREEN");
		ClassSelector labelGreenSelector = new ClassSelector("LABELGREEN");
		ChildCombinator parentsGreen = new ChildCombinator(btnGreenSelector, labelGreenSelector);

		// yellow
		ClassSelector btnYellowSelector = new ClassSelector("BTNYELLOW");
		ClassSelector labelYellowSelector = new ClassSelector("LABELYELLOW");
		ChildCombinator parentsYellow = new ChildCombinator(btnYellowSelector, labelYellowSelector);

		// red
		ClassSelector btnRedSelector = new ClassSelector("BTNRED");
		ClassSelector labelRedSelector = new ClassSelector("LABELRED");
		ChildCombinator parentsRed = new ChildCombinator(btnRedSelector, labelRedSelector);

		// bar
		ClassSelector btnBarSelector = new ClassSelector("BTNBAR");
		ClassSelector labelBarSelector = new ClassSelector("LABELBAR");
		ChildCombinator parentsBar = new ChildCombinator(btnBarSelector, labelBarSelector);

		// bar red
		ClassSelector btnBarRedSelector = new ClassSelector("BTNBARRED");
		ClassSelector labelBarRedSelector = new ClassSelector("LABELBARRED");
		ChildCombinator parentsBarRed = new ChildCombinator(btnBarRedSelector, labelBarRedSelector);

		sts.addRule(parentsBlue, btnBlueStyle);
		sts.addRule(parentsGreen, btnGreenStyle);
		sts.addRule(parentsYellow, btnYellowStyle);
		sts.addRule(parentsRed, btnRedStyle);
		sts.addRule(parentsBar, btnBarStyle);
		sts.addRule(parentsBarRed, btnBarRedStyle);
		sts.addRule(titleSelector, titleStyle);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MicroUI.start();
		InitializeStyle();
		Panel pnl = new Panel();
		Desktop dsk = new Desktop();
		transition = new SlideScreenshotTransitionContainer(MWT.LEFT, false, false);
		transition.show(new MainPage(), false);
		pnl.setWidget(transition);
		pnl.showFullScreen(dsk);
		dsk.show();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}
