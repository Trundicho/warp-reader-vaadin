package de.trundicho.warp.reader.view.ui;

import com.vaadin.ui.Button;
import de.trundicho.warp.reader.core.controller.play.PlayButtonWidgetActionRunner;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;

final class PlayButtonWidgetImpl implements PlayButtonWidget {

	private final Button playButton;

	PlayButtonWidgetImpl(final Button playButton) {
		this.playButton = playButton;
	}

	public void updateWidgetStyle(final PlayState playState) {
		String sign = playState.getSign();
		playButton.setCaption(sign);
	}

	@Override
	public void click() {
		playButton.click();
	}

	@Override
	public void registerChangeListenerAction(PlayButtonWidgetActionRunner action) {
		playButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				action.run();
			}
		});
	}
}