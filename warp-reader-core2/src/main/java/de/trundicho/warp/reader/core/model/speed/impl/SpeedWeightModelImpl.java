package de.trundicho.warp.reader.core.model.speed.impl;

import de.trundicho.warp.reader.core.model.speed.SpeedWeightModel;

import java.util.Collection;
import java.util.HashSet;

public final class SpeedWeightModelImpl implements SpeedWeightModel {
	private double sentenceSpeedWeight;
	private double wordLengthSpeedWeight;
	private final Collection<Listener> listeners;

	public SpeedWeightModelImpl() {
		this.sentenceSpeedWeight = 1.05;
		this.wordLengthSpeedWeight = 1.05;
		this.listeners = new HashSet<>();
	}

	@Override
	public double getPunctuationMarkSpeedWeight() {
		return sentenceSpeedWeight;
	}

	@Override
	public void setSentenceSpeedWeight(double speedWeight) {
		if (speedWeight != sentenceSpeedWeight) {
			sentenceSpeedWeight = speedWeight;
			for (Listener listener : listeners) {
				listener.sentenceSpeedWeightChanged(speedWeight);
			}
		}
	}

	@Override
	public double getWordLengthSpeedWeight() {
		return wordLengthSpeedWeight;
	}

	@Override
	public void setWordLengthSpeedWeight(double speedWeight) {
		if (speedWeight != wordLengthSpeedWeight) {
			wordLengthSpeedWeight = speedWeight;
			for (Listener listener : listeners) {
				listener.wordLengthSpeedWeightChanged(wordLengthSpeedWeight);
			}
		}
	}

	@Override
	public void addListener(Listener listener) {
		listeners.add(listener);

	}

	@Override
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
}