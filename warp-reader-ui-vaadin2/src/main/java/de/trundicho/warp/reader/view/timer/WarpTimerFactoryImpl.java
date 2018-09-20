package de.trundicho.warp.reader.view.timer;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;
import de.trundicho.warp.reader.view.WarpReaderView;

public class WarpTimerFactoryImpl implements WarpTimerFactory {

	private final WarpReaderView ui;

	public WarpTimerFactoryImpl(WarpReaderView ui) {
		this.ui = ui;
	}

	@Override
	public WarpTimer createWarpTimer(WarpUpdater warpUpdater) {
		return new WarpTimerImpl(warpUpdater, ui);
	}

}
