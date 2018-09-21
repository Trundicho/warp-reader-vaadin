package de.trundicho.warp.reader.client.view.gwt.timer;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;

public class WarpTimerFactoryImpl implements WarpTimerFactory {

	@Override
	public WarpTimer createWarpTimer(WarpUpdater warpUpdater) {
		return new WarpTimerImpl(warpUpdater);
	}
}
