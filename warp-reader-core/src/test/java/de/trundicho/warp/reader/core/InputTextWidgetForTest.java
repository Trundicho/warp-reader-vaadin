package de.trundicho.warp.reader.core;

import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;

class InputTextWidgetForTest implements InputTextWidget {
    private String text;
    private String helpText;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    @Override
    public String getText() {
        return text;
    }
}
