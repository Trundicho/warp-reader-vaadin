package de.trundicho.warp.reader.core.model.speed;

public final class SpeedCalculator {

	private final DelayModel speedModel;

	public SpeedCalculator(DelayModel speedModel, SpeedWeightModel speedWeightModel) {
		this.speedModel = speedModel;
	}

	public int computeNextSpeed(final String nextWord) {
		final double defaultDelay = speedModel.getDefaultDelay();
		return Double.valueOf(defaultDelay).intValue();
	}
}