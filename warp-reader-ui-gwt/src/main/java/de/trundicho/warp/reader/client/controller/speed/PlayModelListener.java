package de.trundicho.warp.reader.client.controller.speed;

import de.trundicho.warp.reader.client.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.model.speed.DelayModel;
import de.trundicho.warp.reader.client.model.speed.SpeedCalculator;
import de.trundicho.warp.reader.client.model.warpword.DisplayTextModel;
import de.trundicho.warp.reader.client.model.warpword.WarpWordBuilder;
import de.trundicho.warp.reader.client.model.warpword.impl.WarpWordModel;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;

public class PlayModelListener implements PlayModel.Listener {
	private final WarpTextWidget warpTextLabelUpdater;
	private final DisplayTextModel displayTextModel;
	private final WarpWordBuilder warpWordBuilder;
	private final PlayModeModel playModeModel;
	private final DelayModel speedModel;
	private final SpeedCalculator speedCalculator;

	public PlayModelListener(WarpTextWidget warpTextLabelUpdater, DisplayTextModel displayTextModel,
			WarpWordBuilder warpWordBuilder, PlayModeModel playModeModel, SpeedCalculator speedCalculator,
			DelayModel delayModel) {
		this.warpTextLabelUpdater = warpTextLabelUpdater;
		this.displayTextModel = displayTextModel;
		this.warpWordBuilder = warpWordBuilder;
		this.playModeModel = playModeModel;
		this.speedCalculator = speedCalculator;
		this.speedModel = delayModel;
	}

	@Override
	public void positionUpdate(int currentPositionPercentage, String word) {
		displayTextModel.setText(word);
		final String nextWord;
		if (word != null) {
			nextWord = word;
		} else {
			nextWord = displayTextModel.getText();
			playModeModel.setPlayState(PlayState.PAUSE);
		}
		final int newSpeed = speedCalculator.computeNextSpeed(nextWord);
		speedModel.setVariableDelay(newSpeed);
		String currentWordToDisplay = displayTextModel.getText();
		WarpWordModel updateText = warpWordBuilder.buildWarpWordModel(currentWordToDisplay);
		displayTextModel.setText(nextWord != null ? nextWord.trim() : null);
		warpTextLabelUpdater.setWarpText(updateText.getLeftPart(), updateText.getCenterPart(),
				updateText.getRightPart());
	}
}