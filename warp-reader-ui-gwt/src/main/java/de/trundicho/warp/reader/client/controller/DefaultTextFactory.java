package de.trundicho.warp.reader.client.controller;

public final class DefaultTextFactory {

	private static final DefaultTextFactory INSTANCE = new DefaultTextFactory();

	private DefaultTextFactory() {
	}

	public String createText() {
		return "WarpReader Copy and paste your text or URL into the text field and start reading. As you get faster just increase the tempo as much as you like. Have fun reading at warp speed with WarpReader";
	}

	public static DefaultTextFactory getInstance() {
		return INSTANCE;
	}

}