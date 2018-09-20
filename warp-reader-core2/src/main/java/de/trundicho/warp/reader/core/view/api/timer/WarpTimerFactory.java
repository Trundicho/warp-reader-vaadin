package de.trundicho.warp.reader.core.view.api.timer;

import de.trundicho.warp.reader.core.controller.WarpUpdater;

public interface WarpTimerFactory {
	WarpTimer createWarpTimer(WarpUpdater warpUpdater);
}
