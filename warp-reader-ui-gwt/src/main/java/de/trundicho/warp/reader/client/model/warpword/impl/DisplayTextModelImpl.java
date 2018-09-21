package de.trundicho.warp.reader.client.model.warpword.impl;

import de.trundicho.warp.reader.client.model.warpword.DisplayTextModel;

public final class DisplayTextModelImpl implements DisplayTextModel {
	private String text;

	public DisplayTextModelImpl(String initialString) {
		text = initialString;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}
}