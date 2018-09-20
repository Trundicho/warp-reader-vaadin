package de.trundicho.warp.reader.core.controller.speed;

import de.trundicho.warp.reader.core.model.speed.DurationCalculator;
import de.trundicho.warp.reader.core.model.speed.DelayModel;
import de.trundicho.warp.reader.core.view.api.widgets.DurationWidget;

public class SpeedModelDurationLabelUpdateListener implements DelayModel.SpeedUpdateListener {
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