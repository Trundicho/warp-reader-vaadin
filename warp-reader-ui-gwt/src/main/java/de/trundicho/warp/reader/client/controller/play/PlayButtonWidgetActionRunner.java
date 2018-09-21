package de.trundicho.warp.reader.client.controller.play;

import de.trundicho.warp.reader.client.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;

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