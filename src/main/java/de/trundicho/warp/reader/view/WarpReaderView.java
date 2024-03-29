package de.trundicho.warp.reader.view;

import java.util.Locale;

import de.trundicho.warp.reader.core.controller.Disposer;
import de.trundicho.warp.reader.core.controller.WarpInitializer;
import de.trundicho.warp.reader.core.controller.WarpUpdater;
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
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.core.view.api.widgets.NumberLabelWidget;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;
import de.trundicho.warp.reader.core.view.api.widgets.ReadingPositionBox;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;
import de.trundicho.warp.reader.view.parser.TextAreaParserTimerBuilder;
import de.trundicho.warp.reader.view.timer.WarpTimerImpl;
import de.trundicho.warp.reader.view.ui.CssStyler;
import de.trundicho.warp.reader.view.ui.I18nLocalizer;
import de.trundicho.warp.reader.view.ui.WarpReaderViewBuilderImpl;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Extension;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Timer;
import com.vaadin.ui.UI;

@SpringUI(path = "/")
@Theme("valo")
public class WarpReaderView extends UI {

    private static final int DEFAULT_NUMBER_OF_CHARS_TO_DISPLAY = 15;
    private static final int DEFAULT_WORDS_PER_MINUTE = 200;
    private static final int TEXT_AREA_PARSER_DELAY = 500;
    private I18nLocalizer i18nLocalizer;
    private Disposer disposer;

    @Override
    protected void init(VaadinRequest request) {
        disposer = new Disposer();
        i18nLocalizer = new I18nLocalizer(Locale.ENGLISH);
        PlayModeModel playModeModel = new PlayModeModelImpl(PlayState.PAUSE);
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
    }

    @Override
    public void close() {
        disposer.doDispose();
        super.close();
    }

    private void initUiAndRegisterListeners(WarpReaderViewModel uiModel, WpmSpeedExchanger wpmSpeedExchanger,
                                            DelayModel speedModel, PlayModeModel playModeModel,
                                            SpeedWeightModel speedWeightModel,
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
        WarpTimer warpTimer = new WarpTimerImpl(new WarpUpdater(playModel), this);

        WarpInitializer warpInitializer = new WarpInitializer(warpTextLabelUpdater, speedModel,
                playModeModel, speedWeightModel, textSplitter, playModel, durationWidget, warpTimer);

        TextAreaParserTimerBuilder textAreaParserTimerBuilder = new TextAreaParserTimerBuilder(warpInitializer, i18nLocalizer,
                inputTextWidget);
        Timer textAreaParserTimer = textAreaParserTimerBuilder.buildTextAreaParserTimer(inputTextWidget);
        textAreaParserTimer.scheduleRepeatable(TEXT_AREA_PARSER_DELAY);
        addExtension(textAreaParserTimer);
        disposer.add(() -> textAreaParserTimer.cancel());
        disposer.add(() -> warpInitializer.dispose());
    }

    public void addExtension(Extension extension) {
        super.addExtension(extension);
    }

    private void initAndRegisterReadingPosition(PlayModel playModel, ReadingPositionBox readingPosition, PlayModeModel playModeModel) {
        readingPosition.setReadPositionPercentage(Integer.valueOf(0));
        readingPosition.registerChangeListenerAction(new ReadingPositionPlayModelUpdater(readingPosition, playModel, playModeModel));
        playModel.addListener(new ReadingPositionUpdaterListener(readingPosition));
    }

    private void initInputTextArea(InputTextWidget inputTextWidget) {
        String textToRead = i18nLocalizer.localize("warpreader.initial.text");
        inputTextWidget.setText(textToRead);
        inputTextWidget.setHelpText(i18nLocalizer.localize("warpreader.help.text"));
    }

    private void initAndRegisterWpmBox(WarpReaderViewModel uiModel, WpmSpeedExchanger wpmSpeedExchanger,
                                       DelayModel speedModel) {
        WordsPerMinuteWidget wpmBox = uiModel.getWordsPerMinuteBox();
        double wpm = wpmSpeedExchanger.exchangeToWpm(speedModel.getDefaultDelay());
        wpmBox.setWordsPerMinute((int) wpm);
        wpmBox.registerChangeListenerAction(new WpmBoxSpeedModelUpdater(wpmSpeedExchanger, speedModel, wpmBox));
    }
}