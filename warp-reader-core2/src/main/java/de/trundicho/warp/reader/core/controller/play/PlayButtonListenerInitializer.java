package de.trundicho.warp.reader.core.controller.play;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;

public final class PlayButtonListenerInitializer {
	private final PlayModeModel playModeModel;
	private final PlayButtonWidget playButton;

	public PlayButtonListenerInitializer(PlayModeModel playModeModel, PlayButtonWidget playButton) {
		this.playModeModel = playModeModel;
		this.playButton = playButton;
	}

	public void initListeners() {
		playButton.updateWidgetStyle(playModeModel.getPlayState());
		PlayButtonWidgetActionRunner playButtonWidgetActionRunner = new PlayButtonWidgetActionRunner(playModeModel,
				playButton);
		playButton.registerChangeListenerAction(playButtonWidgetActionRunner);
		PlayModeModel.Listener styleUpdateListener = new PlayButtonModeStyleUpdateListener(playButton);
		playModeModel.addListener(styleUpdateListener);
	}
}