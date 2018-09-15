package de.trundicho.warp.reader.view;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Extension;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Timer;
import com.vaadin.ui.UI;
import de.trundicho.warp.reader.core.controller.DefaultTextFactory;
import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.controller.play.PlayButtonListenerInitializer;
import de.trundicho.warp.reader.core.controller.position.ReadingPositionPlayModelUpdater;
import de.trundicho.warp.reader.core.controller.position.ReadingPositionUpdaterListener;
import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;
import de.trundicho.warp.reader.core.model.i18n.I18nLocalizer;
import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.model.playmode.impl.PlayModeModelImpl;
import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.model.speed.DelayModel;
import de.trundicho.warp.reader.core.model.speed.SpeedWeightModel;
import de.trundicho.warp.reader.core.model.speed.WpmSpeedExchanger;
import de.trundicho.warp.reader.core.model.speed.impl.DelayModelImpl;
import de.trundicho.warp.reader.core.model.speed.impl.SpeedWeightModelImpl;
import de.trundicho.warp.reader.core.model.speed.impl.WpmSpeedExchangerImpl;
import de.trundicho.warp.reader.core.model.warpword.TextSplitter;
import de.trundicho.warp.reader.core.model.warpword.WordLengthModelMutable;
import de.trundicho.warp.reader.core.model.warpword.impl.WordLengthModelImpl;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewBuilder;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModel;
import de.trundicho.warp.reader.core.view.api.WebsiteParserAndWarper;
import de.trundicho.warp.reader.core.view.api.parser.TextAreaParser;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;
import de.trundicho.warp.reader.core.view.api.widgets.*;
import de.trundicho.warp.reader.view.timer.WarpTimerFactoryImpl;
import de.trundicho.warp.reader.view.ui.CssStyler;
import de.trundicho.warp.reader.view.ui.WarpReaderViewBuilderImpl;
import de.trundicho.warp.reader.view.ui.WebsiteParserAndWarperImpl;

import java.util.Locale;

@SpringUI(path = "/")
@Theme("valo")
public class WarpReaderView extends UI {

    private static final int DEFAULT_NUMBER_OF_CHARS_TO_DISPLAY = 15;
    private static final int DEFAULT_WORDS_PER_MINUTE = 260;
    private static final int TEXT_AREA_PARSER_DELAY = 500;
    private I18nLocalizer i18nLocalizer;

    @Override
    protected void init(VaadinRequest request) {
        PlayModeModel playModeModel = new PlayModeModelImpl(PlayState.PLAYING);
        WordLengthModelMutable wordLengthModel = new WordLengthModelImpl(DEFAULT_NUMBER_OF_CHARS_TO_DISPLAY);
        TextSplitter textSplitter = new TextSplitter(wordLengthModel);
        SpeedWeightModel speedWeightModel = new SpeedWeightModelImpl();
        WpmSpeedExchanger wpmSpeedExchanger = new WpmSpeedExchangerImpl();
        DelayModel speedModel = new DelayModelImpl(wpmSpeedExchanger.exchangeToSpeed(DEFAULT_WORDS_PER_MINUTE));
        PlayModel playModel = new PlayModel();

        WarpReaderViewBuilder viewBuilder = new WarpReaderViewBuilderImpl(this);
        WarpReaderViewModel uiModel = viewBuilder.buildView();

        initUiAndRegisterListeners(uiModel, wpmSpeedExchanger, speedModel, playModeModel, speedWeightModel,
                textSplitter, playModel);
        new CssStyler().applyCssStyles();
        i18nLocalizer = new I18nLocalizer(Locale.ENGLISH);
    }

    private void initUiAndRegisterListeners(WarpReaderViewModel uiModel, WpmSpeedExchanger wpmSpeedExchanger,
                                            DelayModel speedModel, PlayModeModel playModeModel, SpeedWeightModel speedWeightModel,
                                            TextSplitter textSplitter, PlayModel playModel) {
        initAndRegisterWpmBox(uiModel, wpmSpeedExchanger, speedModel);

        PlayButtonWidget playButton = uiModel.getPlayButton();
        PlayButtonListenerInitializer playButtonListenerInitializer = new PlayButtonListenerInitializer(playModeModel,
                playButton);
        playButtonListenerInitializer.initListeners();

        InputTextWidget inputTextWidget = uiModel.getInputTextArea();
        initInputTextArea(inputTextWidget);

        ReadingPositionBox readingPosition = uiModel.getReadPosition();
        initAndRegisterReadingPosition(playModel, readingPosition);

        WarpTextWidget warpTextLabelUpdater = uiModel.getWarpTextLabelUpdater();

        DurationWidget durationWidget = uiModel.getDurationLabel();
        WarpTimerFactory warpTimerFactory = new WarpTimerFactoryImpl(playModel, this);

        WarpInitializer warpInitializer = new WarpInitializer(warpTextLabelUpdater, speedModel,
                playModeModel, speedWeightModel, textSplitter, playModel, durationWidget, warpTimerFactory,
                i18nLocalizer);

        WebsiteParserAndWarper websiteParserAndWarper = new WebsiteParserAndWarperImpl(warpInitializer);
        TextAreaParser textAreaParser = new TextAreaParser(warpInitializer, websiteParserAndWarper);
        buildAndStartTextAreaParserTimer(inputTextWidget, textAreaParser, this);
    }

    private void buildAndStartTextAreaParserTimer(InputTextWidget inputTextWidget, TextAreaParser textAreaParser, WarpReaderView ui) {
        Timer textAreaParserTimer = new Timer();
        textAreaParserTimer.run(() ->
                textAreaParser.parseInputTextAndStartWarping(inputTextWidget));
        textAreaParserTimer.scheduleRepeatable(TEXT_AREA_PARSER_DELAY);
        ui.addExtension(textAreaParserTimer);
    }

    public void addExtension(Extension extension) {
        super.addExtension(extension);
    }

    private void initAndRegisterReadingPosition(PlayModel playModel, ReadingPositionBox readingPosition) {
        readingPosition.setReadPositionPercentage(Integer.valueOf(0));
        readingPosition.registerChangeListenerAction(new ReadingPositionPlayModelUpdater(readingPosition, playModel));
        playModel.addListener(new ReadingPositionUpdaterListener(readingPosition));
    }

    private void initInputTextArea(InputTextWidget inputTextWidget) {
        DefaultTextFactory defaultTextFactory = new DefaultTextFactory(i18nLocalizer);
        String textToRead = defaultTextFactory.createText();
        inputTextWidget.setText(textToRead);
    }

    private void initAndRegisterWpmBox(WarpReaderViewModel uiModel, WpmSpeedExchanger wpmSpeedExchanger,
                                       DelayModel speedModel) {
        WordsPerMinuteWidget wpmBox = uiModel.getWordsPerMinuteBox();
        double wpm = wpmSpeedExchanger.exchangeToWpm(speedModel.getDefaultDelay());
        wpmBox.setWordsPerMinute((int) wpm);
        wpmBox.registerChangeListenerAction(new WpmBoxSpeedModelUpdater(wpmSpeedExchanger, speedModel, wpmBox));
    }
}