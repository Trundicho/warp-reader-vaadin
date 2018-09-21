package de.trundicho.warp.reader.client.view.api;

import de.trundicho.warp.reader.client.view.api.widgets.DurationWidget;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.WordsPerMinuteWidget;

public interface WarpReaderViewModel {

	WordsPerMinuteWidget getWordsPerMinuteBox();

	PlayButtonWidget getPlayButton();

	InputTextWidget getInputTextArea();

	WarpTextWidget getWarpTextLabelUpdater();

	DurationWidget getDurationLabel();

	ReadingPositionBox getReadPosition();

}