package de.trundicho.warp.reader.client.controller.position;

import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;

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