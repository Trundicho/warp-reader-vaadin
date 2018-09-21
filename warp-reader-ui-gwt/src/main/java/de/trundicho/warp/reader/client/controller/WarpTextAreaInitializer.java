package de.trundicho.warp.reader.client.controller;

import de.trundicho.warp.reader.client.controller.play.PlayStateListener;
import de.trundicho.warp.reader.client.controller.speed.PlayModelListener;
import de.trundicho.warp.reader.client.controller.speed.SpeedModelDurationLabelUpdateListener;
import de.trundicho.warp.reader.client.controller.speed.TimerSpeedUpdateListener;
import de.trundicho.warp.reader.client.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.model.speed.DelayModel;
import de.trundicho.warp.reader.client.model.speed.DurationCalculator;
import de.trundicho.warp.reader.client.model.speed.SpeedCalculator;
import de.trundicho.warp.reader.client.model.speed.SpeedWeightModel;
import de.trundicho.warp.reader.client.model.warpword.DisplayTextModel;
import de.trundicho.warp.reader.client.model.warpword.TextSplitter;
import de.trundicho.warp.reader.client.model.warpword.WarpWordBuilder;
import de.trundicho.warp.reader.client.model.warpword.impl.DisplayTextModelImpl;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimerFactory;
import de.trundicho.warp.reader.client.view.api.widgets.DurationWidget;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;

public class WarpTextAreaInitializer {

	private final TextSplitter textSplitter;
	private final PlayModeModel playModeModel;
	private final Disposer disposer;
	private final DelayModel speedModel;
	private final DurationWidget durationWidget;
	private final SpeedCalculator speedCalculator;
	private final PlayModel playModel;
	private final WarpTextWidget warpTextLabelUpdater;
	private final WarpTimerFactory warpTimerFactory;
	private final DurationCalculator durationCalculator;

	public WarpTextAreaInitializer(WarpTextWidget warpTextLabelUpdater, DelayModel speedModel,
			PlayModeModel playModeModel, SpeedWeightModel speedWeightModel, TextSplitter textSplitter,
			PlayModel playModel, DurationWidget durationWidget, WarpTimerFactory warpTimerFactory) {
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

	public void initAndStartWarping(InputTextWidget inputTextWidget) {
		String inputText = inputTextWidget.getText();
		disposer.doDispose();
		final String[] combined = getSplittedText(inputText);

		initListeners(speedModel, playModeModel, durationWidget, combined, speedCalculator, playModel,
				warpTextLabelUpdater);
		playModeModel.setPlayState(PlayState.PLAYING);

		inputTextWidget.setText("");
		inputTextWidget.setHelpText("Paste your text or website URL in here...");
	}

	private void initListeners(DelayModel speedModel, PlayModeModel playModeModel, DurationWidget durationWidget,
			final String[] splittedText, SpeedCalculator speedCalculator, PlayModel playModel,
			WarpTextWidget warpTextLabelUpdater) {
		int overallDuration = durationCalculator.computeOverallDuration(splittedText);

		durationWidget.updateDurationLabel(overallDuration);

		final SpeedModelDurationLabelUpdateListener durationLabelListener = new SpeedModelDurationLabelUpdateListener(
				splittedText, durationCalculator, durationWidget);
		speedModel.addListener(durationLabelListener);

		final WarpTimer warpTimer = warpTimerFactory.createWarpTimer();
		final PlayStateListener playStateListener = new PlayStateListener(warpTimer, speedModel);
		playModeModel.addListener(playStateListener);
		final TimerSpeedUpdateListener timerSpeedUpdateListener = new TimerSpeedUpdateListener(speedModel,
				playModeModel, warpTimer);

		DisplayTextModel displayTextModel = new DisplayTextModelImpl(splittedText.length > 0 ? splittedText[0] : "");
		final WarpWordBuilder warpWordBuilder = new WarpWordBuilder();

		PlayModel.Listener playModelListener = new PlayModelListener(warpTextLabelUpdater, displayTextModel,
				warpWordBuilder, playModeModel, speedCalculator, speedModel);

		playModel.addListener(playModelListener);
		playModel.init(splittedText);

		Runnable disposeWarpArea = new Runnable() {

			@Override
			public void run() {
				playModeModel.setPlayState(PlayState.PAUSE);
				playModeModel.removeListener(playStateListener);
				speedModel.removeListener(durationLabelListener);
				speedModel.removeListener(timerSpeedUpdateListener);
				playModel.removeListener(playModelListener);
				warpTimer.cancel();
			}
		};
		disposer.add(disposeWarpArea);
	}

	private String[] getSplittedText(String newText) {
		String textToSplit = newText;
		if (newText == null || "".equals(newText)) {
			textToSplit = DefaultTextFactory.getInstance().createText();
		}
		final String[] split = textSplitter.splitText(textToSplit);
		return textSplitter.getCombined(split);
	}

}
