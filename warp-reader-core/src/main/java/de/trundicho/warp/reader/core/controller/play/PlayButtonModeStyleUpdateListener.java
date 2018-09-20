package de.trundicho.warp.reader.core.controller.play;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;

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