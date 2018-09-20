package de.trundicho.warp.reader.core;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;

class WarpTimerFactoryForTest implements WarpTimerFactory {

    @Override
    public WarpTimer createWarpTimer(WarpUpdater warpUpdater) {
        return new WarpTimerForTest(warpUpdater);
    }


}
