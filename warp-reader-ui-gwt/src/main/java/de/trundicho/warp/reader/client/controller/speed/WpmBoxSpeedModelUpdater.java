package de.trundicho.warp.reader.client.controller.speed;

import de.trundicho.warp.reader.client.model.speed.DelayModel;
import de.trundicho.warp.reader.client.model.speed.WpmSpeedExchanger;
import de.trundicho.warp.reader.client.view.api.widgets.WordsPerMinuteWidget;

public class WpmBoxSpeedModelUpdater implements Runnable {
	private final WpmSpeedExchanger wpmSpeedExchanger;
	private final DelayModel speedModel;
	private final WordsPerMinuteWidget wpmBox;

	public WpmBoxSpeedModelUpdater(WpmSpeedExchanger wpmSpeedExchanger, DelayModel speedModel, WordsPerMinuteWidget wpmBox) {
		this.wpmSpeedExchanger = wpmSpeedExchanger;
		this.speedModel = speedModel;
		this.wpmBox = wpmBox;
	}

	@Override
	public void run() {
		Integer value = wpmBox.getWordsPerMinute();
		double defaultSpeed = wpmSpeedExchanger.exchangeToSpeed(value);
		speedModel.setDefaultDelay(defaultSpeed);
	}
}