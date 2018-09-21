package de.trundicho.warp.reader.client.model.playmode.impl;

import java.util.ArrayList;
import java.util.Collection;

public final class PlayModel {
	private int index = 0;
	private String[] split;

	public interface Listener {

		void positionUpdate(int currentPositionPercentage, String word);

	}

	private final Collection<Listener> listeners = new ArrayList<>();

	public String nextIncrement() {
		if (index < split.length) {
			index += 1;
			if (index < split.length) {
				for (Listener listener : listeners) {
					listener.positionUpdate(getCurrentPositionPercentage(), split[index]);
				}
				return split[index];
			}
		}
		return null;
	}

	public int getCurrentPositionPercentage() {
		return 100 * index / split.length;
	}

	public void setCurrentPosition(int percentage) {
		index = (int) ((percentage / 100.0) * split.length);
		for (Listener listener : listeners) {
			listener.positionUpdate(getCurrentPositionPercentage(), split[index]);
		}
	}

	public void init(String[] split) {
		index = 0;
		this.split = split;
		for (Listener listener : listeners) {
			listener.positionUpdate(getCurrentPositionPercentage(), split[index]);
		}
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		if (listener != null && listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

}