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

	public static final int black = 0x2f2f2f;
	public static final int blue = 0xbae1ff;
	public static final int green = 0xbaffc9;
	public static final int orange = 0xffdfba;
	public static final int red = 0xffb3ba;

	public static final int gold = 0xeed117;

	public static final int blueLabel = 0x1e4fbf;
	public static final int greenLabel = 0x0daf57;
	public static final int orangeLabel = 0xf57a1b;
	public static final int redLabel = 0xf5321b;

	private void InitializeStyle() {
		Stylesheet sts = StyleHelper.getStylesheet();

		// BUTTON Style based on Label & Button classes
		SimpleOutline btnMargin = new SimpleOutline(7);
		SimpleRoundedPlainBackground backgroundButtons = new SimpleRoundedPlainBackground(8);
		SimpleRoundedPlainBackground backgroundButtonsBar = new SimpleRoundedPlainBackground(0);

		// title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setForegroundColor(gold);
		titleStyle.setBackgroundColor(black);
		ComplexRectangularBorder titleBorder = new ComplexRectangularBorder();
		titleStyle.setBorder(titleBorder);
		titleStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// blue button
		EditableStyle btnBlueStyle = new EditableStyle();
		btnBlueStyle.setMargin(btnMargin);
		btnBlueStyle.setPadding(btnMargin);
		btnBlueStyle.setBackground(backgroundButtons);
		btnBlueStyle.setBackgroundColor(blue);
		btnBlueStyle.setForegroundColor(blueLabel);
		btnBlueStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// green button
		EditableStyle btnGreenStyle = new EditableStyle();
		btnGreenStyle.setMargin(btnMargin);
		btnGreenStyle.setPadding(btnMargin);
		btnGreenStyle.setBackground(backgroundButtons);
		btnGreenStyle.setBackgroundColor(green);
		btnGreenStyle.setForegroundColor(greenLabel);
		btnGreenStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// orange button
		EditableStyle btnOrangeStyle = new EditableStyle();
		btnOrangeStyle.setMargin(btnMargin);
		btnOrangeStyle.setPadding(btnMargin);
		btnOrangeStyle.setBackground(backgroundButtons);
		btnOrangeStyle.setBackgroundColor(orange);
		btnOrangeStyle.setForegroundColor(orangeLabel);
		btnOrangeStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// red button
		EditableStyle btnRedStyle = new EditableStyle();
		btnRedStyle.setMargin(btnMargin);
		btnRedStyle.setPadding(btnMargin);
		btnRedStyle.setBackground(backgroundButtons);
		btnRedStyle.setBackgroundColor(red);
		btnRedStyle.setForegroundColor(redLabel);
		btnRedStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// navigation bar button
		EditableStyle btnBarStyle = new EditableStyle();
		btnBarStyle.setBackgroundColor(black);
		btnBarStyle.setForegroundColor(redLabel);
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

		// orange
		ClassSelector btnOrangeSelector = new ClassSelector("BTNORANGE");
		ClassSelector labelOrangeSelector = new ClassSelector("LABELORANGE");
		ChildCombinator parentsOrange = new ChildCombinator(btnOrangeSelector, labelOrangeSelector);

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
		sts.addRule(parentsOrange, btnOrangeStyle);
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
