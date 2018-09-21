package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import de.trundicho.warp.reader.client.controller.play.PlayButtonWidgetActionRunner;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;

final class PlayButtonWidgetImpl implements PlayButtonWidget {

	private final Button playButton;

	PlayButtonWidgetImpl(final Button playButton) {
		this.playButton = playButton;
	}

	public void updateWidgetStyle(final PlayState playState) {
		String sign = playState.getSign();
		playButton.setText(sign);
	}

	@Override
	public void click() {
		playButton.click();
	}

	@Override
	public void registerChangeListenerAction(PlayButtonWidgetActionRunner action) {
		playButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				action.run();
			}
		});
	}
}