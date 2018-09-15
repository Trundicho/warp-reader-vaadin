package de.trundicho.warp.reader.view.timer;

import com.vaadin.ui.Timer;
import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.view.WarpReaderView;

class WarpTimerImpl implements WarpTimer {
    private final Timer timer;

    WarpTimerImpl(WarpUpdater warpUpdater, WarpReaderView ui) {
        timer = new Timer();
        timer.run(() -> doNextWarp(warpUpdater));
        ui.addExtension(timer);
    }

    @Override
    public void doNextWarp(WarpUpdater warpUpdater) {
        warpUpdater.doNextWarp();
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

    @Override
    public void scheduleRepeating(int periodMillis) {
        timer.scheduleRepeatable(periodMillis);

    }
}
