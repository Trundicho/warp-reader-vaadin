package de.trundicho.warp.reader.core.controller.play;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;

public class PlayButtonWidgetActionRunner implements Runnable {
	private final PlayModeModel playModeModel;
	private final PlayButtonWidget playButtonStyleUpdater;

	PlayButtonWidgetActionRunner(PlayModeModel playModeModel, PlayButtonWidget playButtonStyleUpdater) {
		this.playModeModel = playModeModel;
		this.playButtonStyleUpdater = playButtonStyleUpdater;
	}

	@Override
	public void run() {
		final PlayState playState = playModeModel.getPlayState();
		PlayState newPlayState = null;
		if (playState.equals(PlayState.PAUSE)) {
			newPlayState = PlayState.PLAYING;
		} else {
			newPlayState = PlayState.PAUSE;
		}
		playButtonStyleUpdater.updateWidgetStyle(newPlayState);
		playModeModel.setPlayState(newPlayState);
	}

}