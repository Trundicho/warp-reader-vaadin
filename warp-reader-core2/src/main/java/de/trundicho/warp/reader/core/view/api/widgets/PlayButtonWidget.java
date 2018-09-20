package de.trundicho.warp.reader.core.view.api.widgets;

import de.trundicho.warp.reader.core.controller.play.PlayButtonWidgetActionRunner;
import de.trundicho.warp.reader.core.model.playmode.PlayState;

public interface PlayButtonWidget extends ListenerRegistrar<PlayButtonWidgetActionRunner> {
	void updateWidgetStyle(PlayState playState);

	void click();
}