package de.trundicho.warp.reader.client.controller.speed;

import de.trundicho.warp.reader.client.model.speed.DurationCalculator;
import de.trundicho.warp.reader.client.model.speed.DelayModel.SpeedUpdateListener;
import de.trundicho.warp.reader.client.view.api.widgets.DurationWidget;

public class SpeedModelDurationLabelUpdateListener implements SpeedUpdateListener {
	private final String[] split;
	private final DurationCalculator durationCalculator;
	private final DurationWidget durationWidget;

	public SpeedModelDurationLabelUpdateListener(String[] split, DurationCalculator durationCalculator,
			DurationWidget durationWidget) {
		this.split = split;
		this.durationCalculator = durationCalculator;
		this.durationWidget = durationWidget;
	}

	@Override
	public void speedChanged(double oldSpeed, double speed) {
	}

	@Override
	public void defaultSpeedChanged(double oldSpeed, double speed) {
		int overallDuration = durationCalculator.computeOverallDuration(split);
		durationWidget.updateDurationLabel(overallDuration);
	}
}