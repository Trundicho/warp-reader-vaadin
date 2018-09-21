package de.trundicho.warp.reader.client.model.warpword.impl;

import de.trundicho.warp.reader.client.model.warpword.WordLengthModelMutable;

public final class WordLengthModelImpl implements WordLengthModelMutable {

	private int max;
	private int min;

	public WordLengthModelImpl(int minMax) {
		this(minMax, minMax);
	}

	public WordLengthModelImpl(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public int getMin() {
		return min;
	}

	@Override
	public int getMax() {
		return max;
	}

	@Override
	public void setMax(int max) {
		this.max = max;

	}

	@Override
	public void setMin(int min) {
		this.min = min;
	}
}