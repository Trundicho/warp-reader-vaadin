package de.trundicho.warp.reader.client.view.api.widgets;

import de.trundicho.warp.reader.client.controller.play.PlayButtonWidgetActionRunner;
import de.trundicho.warp.reader.client.model.playmode.PlayState;

public interface PlayButtonWidget extends ListenerRegistrar<PlayButtonWidgetActionRunner> {
	void updateWidgetStyle(PlayState playState);

	void click();
}