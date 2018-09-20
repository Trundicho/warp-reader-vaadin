package de.trundicho.warp.reader.core.controller.play;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.model.speed.DelayModel;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;

public class PlayStateListener implements PlayModeModel.Listener {
	private WarpTimer timer;
	private DelayModel speedModel;

	public PlayStateListener(WarpTimer timer, DelayModel speedModel) {
		this.timer = timer;
		this.speedModel = speedModel;
	}

	@Override
	public void playStateChanged(PlayState playState) {
		switch (playState) {
		case PAUSE:
			timer.cancel();
			break;
		case PLAYING:
			timer.scheduleRepeating((int) Math.round(speedModel.getDefaultDelay()));
			break;
		default:
			throw new IllegalStateException("Unknown playstate " + playState);
		}
	}
}