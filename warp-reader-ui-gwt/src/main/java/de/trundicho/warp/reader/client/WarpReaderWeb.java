package de.trundicho.warp.reader.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import de.trundicho.warp.reader.client.view.gwt.DefaultTextFactory;
import de.trundicho.warp.reader.client.view.gwt.WarpReaderViewBuilderImpl;
import de.trundicho.warp.reader.client.view.gwt.WebsiteParserAndWarperImpl;
import de.trundicho.warp.reader.client.view.gwt.timer.WarpTimerFactoryImpl;
import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.controller.play.PlayButtonListenerInitializer;
import de.trundicho.warp.reader.core.controller.position.ReadingPositionPlayModelUpdater;
import de.trundicho.warp.reader.core.controller.position.ReadingPositionUpdaterListener;
import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;
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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WarpReaderWeb implements EntryPoint {

    private static final int DEFAULT_NUMBER_OF_CHARS_TO_DISPLAY = 15;
    private static final int DEFAULT_WORDS_PER_MINUTE = 200;
    private static final int TEXT_AREA_PARSER_DELAY = 500;

    public void onModuleLoad() {
        PlayModeModel playModeModel = new PlayModeModelImpl(PlayState.PAUSE);
        WordLengthModelMutable wordLengthModel = new WordLengthModelImpl(DEFAULT_NUMBER_OF_CHARS_TO_DISPLAY);
        TextSplitter textSplitter = new TextSplitter(wordLengthModel);
        SpeedWeightModel speedWeightModel = new SpeedWeightModelImpl();
        WpmSpeedExchanger wpmSpeedExchanger = new WpmSpeedExchangerImpl();
        DelayModel speedModel = new DelayModelImpl(wpmSpeedExchanger.exchangeToSpeed(DEFAULT_WORDS_PER_MINUTE));
        PlayModel playModel = new PlayModel();

        WarpReaderViewBuilder viewBuilder = new WarpReaderViewBuilderImpl();
        WarpReaderViewModel uiModel = viewBuilder.buildView();

        initUiAndRegisterListeners(uiModel, wpmSpeedExchanger, speedModel, playModeModel, speedWeightModel,
                textSplitter, playModel);
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
        initAndRegisterReadingPosition(playModel, readingPosition, playModeModel);

        WarpTextWidget warpTextLabelUpdater = uiModel.getWarpTextLabelUpdater();

        NumberLabelWidget durationWidget = uiModel.getDurationLabel();
        WarpTimerFactory warpTimerFactory = new WarpTimerFactoryImpl();
        WarpInitializer warpTextAreaInitializer = new WarpInitializer(warpTextLabelUpdater, speedModel,
                playModeModel, speedWeightModel, textSplitter, playModel, durationWidget, warpTimerFactory);

        WebsiteParserAndWarper websiteParserAndWarper = new WebsiteParserAndWarperImpl(warpTextAreaInitializer);
        TextAreaParser textAreaParser = new TextAreaParser(warpTextAreaInitializer, websiteParserAndWarper);
        Timer textAreaParserTimer = new Timer() {

            @Override
            public void run() {
                textAreaParser.parseInputTextAndStartWarping(inputTextWidget);
            }

        };
        textAreaParserTimer.scheduleRepeating(TEXT_AREA_PARSER_DELAY);
    }

    private void initAndRegisterReadingPosition(PlayModel playModel, ReadingPositionBox readingPosition, PlayModeModel playModeModel) {
        readingPosition.setReadPositionPercentage(Integer.valueOf(0));
        readingPosition.registerChangeListenerAction(new ReadingPositionPlayModelUpdater(readingPosition, playModel, playModeModel));
        playModel.addListener(new ReadingPositionUpdaterListener(readingPosition));
    }

    private void initInputTextArea(InputTextWidget inputTextWidget) {
        String textToRead = DefaultTextFactory.getInstance().createText();
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
