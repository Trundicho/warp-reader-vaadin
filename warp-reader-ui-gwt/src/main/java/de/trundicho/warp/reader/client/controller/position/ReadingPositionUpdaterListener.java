package de.trundicho.warp.reader.client.controller.position;

import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;

public class ReadingPositionUpdaterListener implements PlayModel.Listener {
	private final ReadingPositionBox readPosition;

	public ReadingPositionUpdaterListener(ReadingPositionBox readPosition) {
		this.readPosition = readPosition;
	}

	@Override
	public void positionUpdate(int currentPositionPercentage, String word) {
		readPosition.setReadPositionPercentage(currentPositionPercentage);
	}
}