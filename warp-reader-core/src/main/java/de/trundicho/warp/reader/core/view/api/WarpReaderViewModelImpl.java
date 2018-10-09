package de.trundicho.warp.reader.core.view.api;

import de.trundicho.warp.reader.core.view.api.widgets.*;

public class WarpReaderViewModelImpl implements WarpReaderViewModelMutable {
	private WordsPerMinuteWidget wpmBox;
	private PlayButtonWidget playButton;
	private InputTextWidget inputTextWidget;
	private WarpTextWidget warpTextLabelUpdater;
	private NumberLabelWidget durationWidget;
	private ReadingPositionBox wordLengthBox;

	public WordsPerMinuteWidget getWordsPerMinuteBox() {
		return wpmBox;
	}

	public void setWordsPerMinuteBox(WordsPerMinuteWidget wpmBox) {
		this.wpmBox = wpmBox;
	}

	public void setPlayButton(PlayButtonWidget playButton) {
		this.playButton = playButton;
	}

	public PlayButtonWidget getPlayButton() {
		return playButton;
	}

	public InputTextWidget getInputTextArea() {
		return inputTextWidget;
	}

	public void setInputTextArea(InputTextWidget inputTextWidget) {
		this.inputTextWidget = inputTextWidget;
	}

	public WarpTextWidget getWarpTextLabelUpdater() {
		return warpTextLabelUpdater;
	}

	public void setWarpTextLabelUpdater(WarpTextWidget warpTextLabelUpdater) {
		this.warpTextLabelUpdater = warpTextLabelUpdater;
	}

	public NumberLabelWidget getDurationLabel() {
		return durationWidget;
	}

	public void setDurationLabel(NumberLabelWidget durationWidget) {
		this.durationWidget = durationWidget;
	}

	public void setReadPosition(ReadingPositionBox wordLengthBox) {
		this.wordLengthBox = wordLengthBox;
	}

	public ReadingPositionBox getReadPosition() {
		return wordLengthBox;
	}
}