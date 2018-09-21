package de.trundicho.warp.reader.client.view.api;

import de.trundicho.warp.reader.client.view.api.widgets.DurationWidget;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.WordsPerMinuteWidget;

public interface WarpReaderViewModelMutable extends WarpReaderViewModel {

	void setWordsPerMinuteBox(WordsPerMinuteWidget wordsPerMinute);

	void setPlayButton(PlayButtonWidget playButton);

	void setInputTextArea(InputTextWidget inputTextWidget);

	void setWarpTextLabelUpdater(WarpTextWidget warpTextLabelUpdater);

	void setDurationLabel(DurationWidget durationWidget);

	void setReadPosition(ReadingPositionBox wordLengthBox);

}