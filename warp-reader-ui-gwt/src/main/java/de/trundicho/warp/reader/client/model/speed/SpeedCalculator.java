package de.trundicho.warp.reader.client.model.speed;

public final class SpeedCalculator {

	private final DelayModel speedModel;
	private final SpeedWeightModel speedWeightModel;

	public SpeedCalculator(DelayModel speedModel, SpeedWeightModel speedWeightModel) {
		this.speedModel = speedModel;
		this.speedWeightModel = speedWeightModel;
	}

	public int computeNextSpeed(final String nextWord) {
		final double defaultSpeed = speedModel.getDefaultDelay();

		double nextSpeed = 1;
		final double sentenceSeparation = speedWeightModel.getPunctuationMarkSpeedWeight();
		if (nextWord.contains(".") || nextWord.contains("!") || nextWord.contains("?")) {
			nextSpeed = sentenceSeparation;
		} else if (nextWord.contains(",")) {
			nextSpeed = sentenceSeparation;
		}

		final int length = nextWord.length();
		final double wordLengthSpeedWeight = speedWeightModel.getWordLengthSpeedWeight();
		nextSpeed = nextSpeed + (length * wordLengthSpeedWeight - length);
		final int newSpeed = new Double(defaultSpeed * nextSpeed).intValue();
		return newSpeed;
	}
}