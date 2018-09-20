package de.trundicho.warp.reader.core.view.api.widgets;

import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;

public interface WordsPerMinuteWidget extends ListenerRegistrar<WpmBoxSpeedModelUpdater> {
	Integer getWordsPerMinute();

	void setWordsPerMinute(Integer wordsPerMinute);

}
