package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.trundicho.warp.reader.client.services.TextFromWebUrlParserService;
import de.trundicho.warp.reader.client.services.TextFromWebUrlParserServiceAsync;
import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.view.api.WebsiteParserAndWarper;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;

public class WebsiteParserAndWarperImpl implements WebsiteParserAndWarper {
	private final TextFromWebUrlParserServiceAsync boilerplateService = GWT.create(TextFromWebUrlParserService.class);
	private final WarpInitializer warpTextAreaInitializer;

	public WebsiteParserAndWarperImpl(WarpInitializer warpTextAreaInitializer) {
		this.warpTextAreaInitializer = warpTextAreaInitializer;
	}

	@Override
	public void parseWebsiteAndStartWarping(InputTextWidget textArea) {
		String text = textArea.getText();
		AsyncCallback<String> boilerpipeAsyncCallback = new BoilerpipeAsyncCallback(textArea, text,
				warpTextAreaInitializer);
		boilerplateService.parseTextFromWebsite(text, boilerpipeAsyncCallback);
	}
}