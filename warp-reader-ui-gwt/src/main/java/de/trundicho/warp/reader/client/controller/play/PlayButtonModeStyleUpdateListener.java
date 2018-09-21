package de.trundicho.warp.reader.client.controller.play;

import de.trundicho.warp.reader.client.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;

class PlayButtonModeStyleUpdateListener implements PlayModeModel.Listener {
	private final PlayButtonWidget playButtonStyleUpdater;

	PlayButtonModeStyleUpdateListener(PlayButtonWidget playButtonStyleUpdater) {
		this.playButtonStyleUpdater = playButtonStyleUpdater;
	}

	@Override
	public void playStateChanged(PlayState playState) {
		playButtonStyleUpdater.updateWidgetStyle(playState);
	}
}