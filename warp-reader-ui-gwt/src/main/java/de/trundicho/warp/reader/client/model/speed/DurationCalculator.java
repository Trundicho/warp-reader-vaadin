package de.trundicho.warp.reader.client.model.speed;

public final class DurationCalculator {

	private SpeedCalculator speedCalculator;

	public DurationCalculator(SpeedCalculator speedCalculator) {
		this.speedCalculator = speedCalculator;
	}

	public int computeOverallDuration(String[] splittedText) {
		int overallDuration = 0;
		for (int i = 0; i < splittedText.length; i++) {
			overallDuration += speedCalculator.computeNextSpeed(splittedText[i]);
		}
		return overallDuration;
	}
}