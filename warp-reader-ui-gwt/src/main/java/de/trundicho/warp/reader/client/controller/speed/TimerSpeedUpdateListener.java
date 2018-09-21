package de.trundicho.warp.reader.client.controller.speed;

import de.trundicho.warp.reader.client.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.client.model.playmode.PlayState;
import de.trundicho.warp.reader.client.model.speed.DelayModel;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimer;

public class TimerSpeedUpdateListener implements DelayModel.SpeedUpdateListener {
	private final PlayModeModel playModeModel;
	private final WarpTimer timer;

	public TimerSpeedUpdateListener(DelayModel speedModel, PlayModeModel playModeModel, WarpTimer timer) {
		this.playModeModel = playModeModel;
		this.timer = timer;
		double delay = speedModel.getDefaultDelay();
		updateTimerSpeed(delay, delay);
	}

	@Override
	public void speedChanged(double oldSpeed, double speed) {
		updateTimerSpeed(oldSpeed, speed);
	}

	private void updateTimerSpeed(double oldSpeed, double speed) {
		PlayState playState = playModeModel.getPlayState();
		switch (playState) {
		case PLAYING:
			timer.scheduleRepeating((int) Math.round(speed));
			break;
		default:
			break;
		}
	}

	@Override
	public void defaultSpeedChanged(double oldSpeed, double speed) {
	}
}