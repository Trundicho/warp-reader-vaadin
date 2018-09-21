package de.trundicho.warp.reader.client.view.gwt.timer;

import com.google.gwt.user.client.Timer;
import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;

class WarpTimerImpl extends Timer implements WarpTimer {

	private final WarpUpdater warpUpdater;

	WarpTimerImpl(WarpUpdater warpUpdater){
		this.warpUpdater = warpUpdater;
	}

	@Override
	public void doNextWarp(WarpUpdater warpUpdater) {
		warpUpdater.doNextWarp();
	}

	@Override
	public void run() {
		doNextWarp(warpUpdater);
	}

	@Override
	public void cancel() {
		super.cancel();
	}

	@Override
	public void scheduleRepeating(int periodMillis) {
		super.scheduleRepeating(periodMillis);

	}
}
