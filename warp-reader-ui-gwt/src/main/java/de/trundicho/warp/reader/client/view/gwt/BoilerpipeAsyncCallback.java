package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.trundicho.warp.reader.client.controller.WarpTextAreaInitializer;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;

public class BoilerpipeAsyncCallback implements AsyncCallback<String> {
	private final InputTextWidget textArea;
	private final String text;
	private final WarpTextAreaInitializer warpTextAreaInitializer;
	private final String errorText;

	BoilerpipeAsyncCallback(InputTextWidget textArea, String text, WarpTextAreaInitializer warpTextAreaInitializer) {
		this.textArea = textArea;
		this.text = text;
		this.warpTextAreaInitializer = warpTextAreaInitializer;
		this.errorText = "Error occured: Please try other URL.";
	}

	@Override
	public void onFailure(Throwable caught) {
		textArea.setText(errorText);
		warpTextAreaInitializer.initAndStartWarping(textArea);
		textArea.setHelpText(errorText + "\nCan not parse " + text);
	}

	@Override
	public void onSuccess(String result) {
		boolean error = false;
		if (result != null && !result.equals(text)) {
			textArea.setText(result);
		} else {
			textArea.setText(errorText);
			error = true;
		}
		warpTextAreaInitializer.initAndStartWarping(textArea);
		if (error) {
			textArea.setHelpText(errorText + "\nCan not parse " + text);
		}
	}

}