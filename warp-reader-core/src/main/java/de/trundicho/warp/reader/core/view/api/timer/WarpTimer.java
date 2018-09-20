package de.trundicho.warp.reader.core.view.api.timer;

import de.trundicho.warp.reader.core.controller.WarpUpdater;

public interface WarpTimer extends WarpReaderTimer {
	void doNextWarp(WarpUpdater warpUpdater);
}
