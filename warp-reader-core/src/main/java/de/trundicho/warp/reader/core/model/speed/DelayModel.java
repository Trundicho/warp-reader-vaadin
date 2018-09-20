package de.trundicho.warp.reader.core.model.speed;

/**
 * Created by angeloromito on 11.04.14.
 */
public interface DelayModel {

	interface SpeedUpdateListener {
		void speedChanged(double oldSpeed, double speed);

		void defaultSpeedChanged(double oldSpeed, double speed);
	}

	double getVariableDelay();

	double getDefaultDelay();

	void setVariableDelay(double speed);

	void setDefaultDelay(double speed);

	void addListener(SpeedUpdateListener listener);

	void removeListener(SpeedUpdateListener listener);

	void dispose();
}
