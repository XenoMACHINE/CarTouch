/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

import ej.style.font.FontProfile;
import ej.style.font.loader.AbstractFontLoader;

/**
 *
 */
public class MyFontLoader extends AbstractFontLoader {

	@Override
	protected int getFontHeight(FontProfile fontProfile) {
		// TODO Auto-generated method stub

		switch (fontProfile.getSize()) {
		case LARGE:
		case MEDIUM:
			return 35;
		case SMALL:
		default:
			return 30;
		}
	}

}
