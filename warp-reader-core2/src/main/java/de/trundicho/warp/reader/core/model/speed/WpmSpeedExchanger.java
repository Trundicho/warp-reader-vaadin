package de.trundicho.warp.reader.core.model.speed;

/**
 * Created by angeloromito on 12.04.14.
 */
public interface WpmSpeedExchanger {

	double exchangeToWpm(double speed);

	double exchangeToSpeed(double wordsPerMinute);
}
