package de.trundicho.warp.reader.view.parser;

import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.view.api.WebsiteParserAndWarper;
import de.trundicho.warp.reader.core.view.api.parser.TextAreaParser;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.view.ui.I18nLocalizer;
import de.trundicho.warp.reader.view.ui.WebsiteParserAndWarperImpl;

import com.vaadin.ui.Timer;

public class TextAreaParserTimerBuilder {

    private final TextAreaParser textAreaParser;

    public TextAreaParserTimerBuilder(WarpInitializer warpInitializer, I18nLocalizer i18nLocalizer,
                                      InputTextWidget inputTextWidget) {
        WebsiteParserAndWarper websiteParserAndWarper = new WebsiteParserAndWarperImpl(warpInitializer, i18nLocalizer,
                inputTextWidget);
        this.textAreaParser = new TextAreaParser(warpInitializer, websiteParserAndWarper);
    }

    public Timer buildTextAreaParserTimer(InputTextWidget inputTextWidget) {
        Timer textAreaParserTimer = new Timer();
        textAreaParserTimer.run(() ->
                {
                    textAreaParser.parseInputTextAndStartWarping(inputTextWidget.getText());
                    inputTextWidget.setText("");
                }
        );
        return textAreaParserTimer;
    }

}
