package de.trundicho.warp.reader.view.ui;

import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.view.api.widgets.PlayButtonWidget;

import com.vaadin.ui.Button;

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
	public void registerChangeListenerAction(Runnable action) {
		playButton.addClickListener((Button.ClickListener) clickEvent -> action.run());
	}
}