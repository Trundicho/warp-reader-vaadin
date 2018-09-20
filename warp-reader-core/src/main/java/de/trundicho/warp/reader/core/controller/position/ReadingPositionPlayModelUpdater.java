package de.trundicho.warp.reader.core.controller.position;

import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.view.api.widgets.ReadingPositionBox;

public class ReadingPositionPlayModelUpdater implements Runnable {
	private final ReadingPositionBox wordLengthBox;
	private final PlayModel playModel;

	public ReadingPositionPlayModelUpdater(ReadingPositionBox wordLengthBox, PlayModel playModel) {
		this.wordLengthBox = wordLengthBox;
		this.playModel = playModel;
	}

	@Override
	public void run() {
		Integer value = wordLengthBox.getReadPositionPercentage();
		playModel.setCurrentPosition(value);
	}

}