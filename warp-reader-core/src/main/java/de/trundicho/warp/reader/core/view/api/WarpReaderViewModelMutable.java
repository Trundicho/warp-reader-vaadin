package de.trundicho.warp.reader.core.view.api;

import de.trundicho.warp.reader.core.view.api.widgets.*;

public interface WarpReaderViewModelMutable extends WarpReaderViewModel {

	void setWordsPerMinuteBox(WordsPerMinuteWidget wordsPerMinute);

	void setPlayButton(PlayButtonWidget playButton);

	void setInputTextArea(InputTextWidget inputTextWidget);

	void setWarpTextLabelUpdater(WarpTextWidget warpTextLabelUpdater);

	void setDurationLabel(DurationWidget durationWidget);

	void setReadPosition(ReadingPositionBox wordLengthBox);

}