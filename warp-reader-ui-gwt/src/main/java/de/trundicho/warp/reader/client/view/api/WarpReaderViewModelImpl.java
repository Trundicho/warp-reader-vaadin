package de.trundicho.warp.reader.client.view.api;

import de.trundicho.warp.reader.client.view.api.widgets.DurationWidget;
import de.trundicho.warp.reader.client.view.api.widgets.InputTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.PlayButtonWidget;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.WordsPerMinuteWidget;

public class WarpReaderViewModelImpl implements WarpReaderViewModelMutable {
	private WordsPerMinuteWidget wpmBox;
	private PlayButtonWidget playButton;
	private InputTextWidget inputTextWidget;
	private WarpTextWidget warpTextLabelUpdater;
	private DurationWidget durationWidget;
	private ReadingPositionBox wordLengthBox;

	@Override
	public WordsPerMinuteWidget getWordsPerMinuteBox() {
		return wpmBox;
	}

	@Override
	public void setWordsPerMinuteBox(WordsPerMinuteWidget wpmBox) {
		this.wpmBox = wpmBox;
	}

	@Override
	public void setPlayButton(PlayButtonWidget playButton) {
		this.playButton = playButton;
	}

	@Override
	public PlayButtonWidget getPlayButton() {
		return playButton;
	}

	@Override
	public InputTextWidget getInputTextArea() {
		return inputTextWidget;
	}

	@Override
	public void setInputTextArea(InputTextWidget inputTextWidget) {
		this.inputTextWidget = inputTextWidget;
	}

	@Override
	public WarpTextWidget getWarpTextLabelUpdater() {
		return warpTextLabelUpdater;
	}

	@Override
	public void setWarpTextLabelUpdater(WarpTextWidget warpTextLabelUpdater) {
		this.warpTextLabelUpdater = warpTextLabelUpdater;
	}

	@Override
	public DurationWidget getDurationLabel() {
		return durationWidget;
	}

	@Override
	public void setDurationLabel(DurationWidget durationWidget) {
		this.durationWidget = durationWidget;
	}

	@Override
	public void setReadPosition(ReadingPositionBox wordLengthBox) {
		this.wordLengthBox = wordLengthBox;
	}

	@Override
	public ReadingPositionBox getReadPosition() {
		return wordLengthBox;
	}
}