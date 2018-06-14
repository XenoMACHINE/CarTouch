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
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
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
		EditableStyle btnStyle = new EditableStyle();
		SimpleOutline btnMargin = new SimpleOutline(2);
		btnStyle.setMargin(btnMargin);
		btnStyle.setPadding(btnMargin);
		SimpleOutline myOutline = new SimpleOutline(2);
		btnStyle.setMargin(myOutline);
		SimpleRoundedPlainBackground myBackground = new SimpleRoundedPlainBackground(20);
		btnStyle.setBackground(myBackground);
		btnStyle.setBackgroundColor(Colors.NAVY);
		btnStyle.setForegroundColor(Colors.YELLOW);
		btnStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER); // Rule for labels child of buttons
		TypeSelector lblSel = new TypeSelector(Label.class);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);
		ChildCombinator parentBtnSel = new ChildCombinator(btnSel, lblSel);

		// title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setForegroundColor(Colors.BLACK);
		titleStyle.setBackgroundColor(Colors.SILVER);
		ComplexRectangularBorder titleBorder = new ComplexRectangularBorder();
		titleBorder.setBottom(2);
		titleStyle.setBorder(titleBorder);
		titleStyle.setBorderColor(Colors.GRAY);

		// green button

		// yellow button

		// red button

		ClassSelector titleSelector = new ClassSelector("TITLE");

		sts.addRule(parentBtnSel, btnStyle);
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
