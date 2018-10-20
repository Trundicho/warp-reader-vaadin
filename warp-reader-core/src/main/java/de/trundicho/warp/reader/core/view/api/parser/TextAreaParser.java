package de.trundicho.warp.reader.core.view.api.parser;

import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.view.api.WebsiteParserAndWarper;

public final class TextAreaParser {

    private final WarpInitializer warpInitializer;
    private final WebsiteParserAndWarper websiteParserAndWarper;

    public TextAreaParser(WarpInitializer warpInitializer,
                          WebsiteParserAndWarper websiteParserAndWarper) {
        this.warpInitializer = warpInitializer;
        this.websiteParserAndWarper = websiteParserAndWarper;
    }

    public void parseInputTextAndStartWarping(String text) {
        if (text != null && !text.isEmpty()) {
            if (text.startsWith("http")) {
                websiteParserAndWarper.parseWebsiteAndStartWarping(text);
            } else {
                warpInitializer.initAndStartWarping(text);
            }
        }
    }

}