package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.IntegerBox;
import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;

class WordsPerMinuteBoxImpl implements WordsPerMinuteWidget {
	private IntegerBox wordsPerMinuteBox;

	WordsPerMinuteBoxImpl(IntegerBox wordsPerMinuteBox) {
		this.wordsPerMinuteBox = wordsPerMinuteBox;
	}

	@Override
	public Integer getWordsPerMinute() {
		return wordsPerMinuteBox.getValue();
	}

	@Override
	public void setWordsPerMinute(Integer wordsPerMinute) {
		wordsPerMinuteBox.setValue(wordsPerMinute);
	}

	@Override
	public void registerChangeListenerAction(WpmBoxSpeedModelUpdater action) {
		wordsPerMinuteBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				action.run();
			}
		});
	}

}
