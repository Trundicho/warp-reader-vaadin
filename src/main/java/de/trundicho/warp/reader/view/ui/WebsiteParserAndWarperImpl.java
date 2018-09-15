package de.trundicho.warp.reader.view.ui;

import de.trundicho.warp.reader.core.controller.WarpTextAreaInitializer;
import de.trundicho.warp.reader.core.view.api.WebsiteParserAndWarper;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.view.parser.TextFromWebUrlParserService;

public class WebsiteParserAndWarperImpl implements WebsiteParserAndWarper {
    private final TextFromWebUrlParserService boilerplateService;
    private final WarpTextAreaInitializer warpTextAreaInitializer;
    private final String errorText;

    public WebsiteParserAndWarperImpl(WarpTextAreaInitializer warpTextAreaInitializer) {
        this.warpTextAreaInitializer = warpTextAreaInitializer;
        this.errorText = "Error occured: Please try other URL.";
        this.boilerplateService = new TextFromWebUrlParserService();
    }

    public void parseWebsiteAndStartWarping(InputTextWidget textArea) {
        String text = textArea.getText();
        try {
            final String textFromWebsite = boilerplateService.parseTextFromWebsite(text);
            boolean error = false;
            if (textFromWebsite != null && !textFromWebsite.equals(text)) {
                textArea.setText(textFromWebsite);
            } else {
                textArea.setText(errorText);
                error = true;
            }
            warpTextAreaInitializer.initAndStartWarping(textArea);
            if (error) {
                textArea.setHelpText(errorText + "\nCan not parse " + text);
            }
        } catch (Exception e) {
            onFailure(textArea, text, e);
        }
    }

    public void onFailure(InputTextWidget textArea, String text, Throwable caught) {
        textArea.setText(errorText);
        warpTextAreaInitializer.initAndStartWarping(textArea);
        textArea.setHelpText(errorText + "\nCan not parse " + text);
    }
}