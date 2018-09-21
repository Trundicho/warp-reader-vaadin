package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.ui.TextArea;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;

class InputTextAreaImpl implements InputTextWidget {

	private final TextArea inputTextArea;

	InputTextAreaImpl(TextArea inputTextArea) {
		this.inputTextArea = inputTextArea;

	}

	@Override
	public void setText(String text) {
		inputTextArea.setText(text);
	}

	@Override
	public void setHelpText(String helpText) {
		inputTextArea.getElement().setPropertyString("placeholder", helpText);
	}

	@Override
	public String getText() {
		return inputTextArea.getText();
	}

}
