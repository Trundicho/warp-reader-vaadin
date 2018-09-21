package de.trundicho.warp.reader.client.view.gwt.timer;

import com.google.gwt.user.client.Timer;

import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimer;

class WarpTimerImpl extends Timer implements WarpTimer {
	private final PlayModel playModel;

	WarpTimerImpl(PlayModel playModel) {
		this.playModel = playModel;
	}

	@Override
	public void doNextWarp() {
		playModel.nextIncrement();
	}

	@Override
	public void run() {
		doNextWarp();
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
