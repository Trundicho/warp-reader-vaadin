package de.trundicho.warp.reader.core.model.speed.impl;

import de.trundicho.warp.reader.core.model.speed.DelayModel;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by angeloromito on 11.04.14.
 */
public final class DelayModelImpl implements DelayModel {
	private final Collection<SpeedUpdateListener> listeners;
	private double defaultDelay;
	private double delay;

	public DelayModelImpl(final double defaultDelay) {
		this.delay = defaultDelay;
		this.defaultDelay = defaultDelay;
		listeners = new HashSet<SpeedUpdateListener>();
	}

	@Override
	public double getVariableDelay() {
		return delay;
	}

	@Override
	public double getDefaultDelay() {
		return defaultDelay;
	}

	@Override
	public void setVariableDelay(double speed) {
		if (this.delay != speed) {
			double oldSpeed = this.delay;
			this.delay = speed;
			for (SpeedUpdateListener listener : listeners) {
				listener.speedChanged(oldSpeed, speed);
			}
		}
	}

	@Override
	public void setDefaultDelay(double speed) {
		double oldDefaultSpeed = defaultDelay;
		double oldSpeed = delay;
		this.defaultDelay = speed;
		for (SpeedUpdateListener listener : listeners) {
			listener.defaultSpeedChanged(oldDefaultSpeed, defaultDelay);
			listener.speedChanged(oldSpeed, speed);
		}
	}

	@Override
	public void addListener(SpeedUpdateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(SpeedUpdateListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispose() {
		listeners.clear();
	}
}
