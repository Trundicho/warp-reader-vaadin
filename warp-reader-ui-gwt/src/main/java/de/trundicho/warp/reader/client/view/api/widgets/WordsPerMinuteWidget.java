package de.trundicho.warp.reader.client.view.api.widgets;

import de.trundicho.warp.reader.client.controller.speed.WpmBoxSpeedModelUpdater;

public interface WordsPerMinuteWidget extends ListenerRegistrar<WpmBoxSpeedModelUpdater> {
	Integer getWordsPerMinute();

	void setWordsPerMinute(Integer wordsPerMinute);

}
