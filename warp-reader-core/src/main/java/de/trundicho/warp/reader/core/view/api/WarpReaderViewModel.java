package de.trundicho.warp.reader.core.view.api;

import de.trundicho.warp.reader.core.view.api.widgets.*;

public interface WarpReaderViewModel {

	WordsPerMinuteWidget getWordsPerMinuteBox();

	PlayButtonWidget getPlayButton();

	InputTextWidget getInputTextArea();

	WarpTextWidget getWarpTextLabelUpdater();

	NumberLabelWidget getDurationLabel();

	ReadingPositionBox getReadPosition();

}