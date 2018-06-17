/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package moc.lab;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.wadapps.storage.Storage;
import moc.lab.GameWidget.Level;
import moc.lab.pages.PlayPage;

/**
 *
 */
public class StorageManager {

	private static StorageManager storageManager;

	Storage storage = ServiceLoaderFactory.getServiceLoader().getService(Storage.class);

	public StorageManager() {
		// TODO Auto-generated constructor stub

	}

	public static StorageManager getInstance() {
		if (storageManager == null) {
			storageManager = new StorageManager();
		}
		return storageManager;
	}

	public String getKey(GameWidget.Level level) {
		String key = "score";
		if (level == Level.MEDIUM) {
			key += "medium";
		}
		if (level == Level.HARD) {
			key += "hard";
		}

		return key;
	}

	public boolean hasKey(String key) {
		boolean hasKey = false;

		try {
			for (String k : this.storage.getIds()) {
				if (k.equals(key)) {
					hasKey = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hasKey;
	}

	public void writeScoreInStorage(String key) {

		String prefix = "";
		if (hasKey(key)) {
			prefix = getScores(key) + ",";
		}

		try (ByteArrayInputStream bais = new ByteArrayInputStream((prefix + PlayPage.score).getBytes())) {
			this.storage.store(key, bais);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getScores(String key) {

		if (!hasKey(key)) {
			return "";
		}

		try (InputStream stream = this.storage.load(key)) {

			final int bufferSize = 1024;
			final char[] buffer = new char[bufferSize];
			final StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(stream, "UTF-8");
			for (;;) {
				int rsz = in.read(buffer, 0, buffer.length);
				if (rsz < 0) {
					break;
				}
				out.append(buffer, 0, rsz);
			}
			System.out.println(out);
			return out.toString();
			// Do something with the input stream.
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
