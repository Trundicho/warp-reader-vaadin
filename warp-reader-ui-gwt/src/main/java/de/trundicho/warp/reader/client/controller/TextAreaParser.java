package de.trundicho.warp.reader.client.controller;

import de.trundicho.warp.reader.client.view.api.WebsiteParserAndWarper;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;

public final class TextAreaParser {

	private final WarpTextAreaInitializer warpTextAreaInitializer;
	private final WebsiteParserAndWarper websiteParserAndWarper;

	public TextAreaParser(WarpTextAreaInitializer warpTextAreaInitializer,
			WebsiteParserAndWarper websiteParserAndWarper) {
		this.warpTextAreaInitializer = warpTextAreaInitializer;
		this.websiteParserAndWarper = websiteParserAndWarper;
	}

	public void parseInputTextAndStartWarping(InputTextWidget textArea) {
		String text = textArea.getText();
		if (text != null && !text.isEmpty()) {
			if (text.startsWith("http")) {
				websiteParserAndWarper.parseWebsiteAndStartWarping(textArea);
			} else {
				warpTextAreaInitializer.initAndStartWarping(textArea);
			}
		}
	}

}