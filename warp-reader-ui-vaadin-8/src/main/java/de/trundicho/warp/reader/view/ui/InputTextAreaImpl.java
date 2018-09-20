package de.trundicho.warp.reader.view.ui;

import com.vaadin.ui.TextArea;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;

class InputTextAreaImpl implements InputTextWidget {

	private final TextArea inputTextArea;

	InputTextAreaImpl(TextArea inputTextArea) {
		this.inputTextArea = inputTextArea;

	}

	@Override
	public void setText(String text) {
		inputTextArea.setValue(text);
	}

	@Override
	public void setHelpText(String helpText) {
		inputTextArea.setPlaceholder(helpText);
	}

	@Override
	public String getText() {
		return inputTextArea.getValue();
	}

}
