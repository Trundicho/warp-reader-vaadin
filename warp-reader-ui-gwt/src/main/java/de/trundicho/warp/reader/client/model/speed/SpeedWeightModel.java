package de.trundicho.warp.reader.client.model.speed;

public interface SpeedWeightModel {
	interface Listener {
		void sentenceSpeedWeightChanged(double speedWeight);

		void wordLengthSpeedWeightChanged(double speedWeight);
	}

	double getPunctuationMarkSpeedWeight();

	void setSentenceSpeedWeight(double speedWeight);

	double getWordLengthSpeedWeight();

	void setWordLengthSpeedWeight(double speedWeight);

	void addListener(Listener listener);

	void removeListener(Listener listener);
}