package de.trundicho.warp.reader.core.model.speed.impl;

import de.trundicho.warp.reader.core.model.speed.WpmSpeedExchanger;

/**
 * Created by angeloromito on 12.04.14.
 */
public final class WpmSpeedExchangerImpl implements WpmSpeedExchanger {

	@Override
	public double exchangeToWpm(double speed) {
		return 60000.0 / speed;
	}

	@Override
	public double exchangeToSpeed(double wordsPerMinute) {
		return 60000.0 / wordsPerMinute;
	}
}
