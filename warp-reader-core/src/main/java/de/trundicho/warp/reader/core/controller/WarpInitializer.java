package de.trundicho.warp.reader.core.controller;

import de.trundicho.warp.reader.core.controller.play.PlayStateListener;
import de.trundicho.warp.reader.core.controller.speed.PlayModelListener;
import de.trundicho.warp.reader.core.controller.speed.SpeedModelDurationLabelUpdateListener;
import de.trundicho.warp.reader.core.controller.speed.TimerSpeedUpdateListener;
import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.model.speed.DelayModel;
import de.trundicho.warp.reader.core.model.speed.DurationCalculator;
import de.trundicho.warp.reader.core.model.speed.SpeedCalculator;
import de.trundicho.warp.reader.core.model.speed.SpeedWeightModel;
import de.trundicho.warp.reader.core.model.warpword.DisplayTextModel;
import de.trundicho.warp.reader.core.model.warpword.TextSplitter;
import de.trundicho.warp.reader.core.model.warpword.WarpWordBuilder;
import de.trundicho.warp.reader.core.model.warpword.impl.DisplayTextModelImpl;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;
import de.trundicho.warp.reader.core.view.api.widgets.NumberLabelWidget;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;

public class WarpInitializer {

    private final TextSplitter textSplitter;
    private final PlayModeModel playModeModel;
    private final Disposer disposer;
    private final DelayModel speedModel;
    private final NumberLabelWidget durationWidget;
    private final SpeedCalculator speedCalculator;
    private final PlayModel playModel;
    private final WarpTextWidget warpTextLabelUpdater;
    private final WarpTimerFactory warpTimerFactory;
    private final DurationCalculator durationCalculator;

    public WarpInitializer(WarpTextWidget warpTextLabelUpdater, DelayModel speedModel,
                           PlayModeModel playModeModel, SpeedWeightModel speedWeightModel, TextSplitter textSplitter,
                           PlayModel playModel, NumberLabelWidget durationWidget, WarpTimerFactory warpTimerFactory) {
        this.warpTextLabelUpdater = warpTextLabelUpdater;
        this.speedModel = speedModel;
        this.playModeModel = playModeModel;
        this.textSplitter = textSplitter;
        this.playModel = playModel;
        this.durationWidget = durationWidget;
        this.warpTimerFactory = warpTimerFactory;
        this.disposer = new Disposer();
        this.speedCalculator = new SpeedCalculator(speedModel, speedWeightModel);
        this.durationCalculator = new DurationCalculator(speedCalculator);
    }

    public void initAndStartWarping(String inputText) {
        disposer.doDispose();
        final String[] combined = getSplittedText(inputText);

        initListeners(speedModel, playModeModel, durationWidget, combined, speedCalculator, playModel,
                warpTextLabelUpdater);
        playModeModel.setPlayState(PlayState.PLAYING);
    }

    public void dispose(){
        disposer.doDispose();
    }

    private void initListeners(DelayModel speedModel, PlayModeModel playModeModel, NumberLabelWidget durationWidget,
                               final String[] splittedText, SpeedCalculator speedCalculator, PlayModel playModel,
                               WarpTextWidget warpTextLabelUpdater) {
        int overallDuration = durationCalculator.computeOverallDuration(splittedText);

        durationWidget.updateNumberLabel(overallDuration);

        final SpeedModelDurationLabelUpdateListener durationLabelListener = new SpeedModelDurationLabelUpdateListener(
                splittedText, durationCalculator, durationWidget);
        speedModel.addListener(durationLabelListener);

        final WarpTimer warpTimer = warpTimerFactory.createWarpTimer(new WarpUpdater(playModel));
        final PlayStateListener playStateListener = new PlayStateListener(warpTimer, speedModel);
        playModeModel.addListener(playStateListener);
        final TimerSpeedUpdateListener timerSpeedUpdateListener = new TimerSpeedUpdateListener(speedModel,
                playModeModel, warpTimer);
        speedModel.addListener(timerSpeedUpdateListener);
        DisplayTextModel displayTextModel = new DisplayTextModelImpl(splittedText.length > 0 ? splittedText[0] : "");
        final WarpWordBuilder warpWordBuilder = new WarpWordBuilder();

        PlayModel.Listener playModelListener = new PlayModelListener(warpTextLabelUpdater, displayTextModel,
                warpWordBuilder, playModeModel, speedCalculator, speedModel);

        playModel.addListener(playModelListener);
        playModel.init(splittedText);

        Runnable disposeWarpArea = new DisposeRunnable(playModeModel, playStateListener,
                speedModel, durationLabelListener, timerSpeedUpdateListener,
                playModel, playModelListener, warpTimer);
        disposer.add(disposeWarpArea);
    }

    private String[] getSplittedText(String newText) {
        String textToSplit = newText;
        if (newText == null) {
            textToSplit = "";
        }
        final String[] split = textSplitter.splitText(textToSplit);
        return textSplitter.getCombined(split);
    }

    private static class DisposeRunnable implements Runnable {

        private final PlayModeModel playModeModel;
        private final PlayStateListener playStateListener;
        private final DelayModel speedModel;
        private final SpeedModelDurationLabelUpdateListener durationLabelListener;
        private final TimerSpeedUpdateListener timerSpeedUpdateListener;
        private final PlayModel playModel;
        private final PlayModel.Listener playModelListener;
        private final WarpTimer warpTimer;

        DisposeRunnable(PlayModeModel playModeModel, PlayStateListener playStateListener,
                        DelayModel speedModel, SpeedModelDurationLabelUpdateListener durationLabelListener,
                        TimerSpeedUpdateListener timerSpeedUpdateListener, PlayModel playModel,
                        PlayModel.Listener playModelListener, WarpTimer warpTimer) {
            this.playModeModel = playModeModel;
            this.playStateListener = playStateListener;
            this.speedModel = speedModel;
            this.durationLabelListener = durationLabelListener;
            this.timerSpeedUpdateListener = timerSpeedUpdateListener;
            this.playModel = playModel;
            this.playModelListener = playModelListener;
            this.warpTimer = warpTimer;
        }

        @Override
        public void run() {
            playModeModel.setPlayState(PlayState.PAUSE);
            playModeModel.removeListener(playStateListener);
            speedModel.removeListener(durationLabelListener);
            speedModel.removeListener(timerSpeedUpdateListener);
            playModel.removeListener(playModelListener);
            warpTimer.cancel();
        }
    }
}
