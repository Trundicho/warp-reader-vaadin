package de.trundicho.warp.reader.client.view.gwt.timer;

import com.google.gwt.user.client.Timer;
import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;

public class WarpTimerImpl implements WarpTimer {

    private final WarpUpdater warpUpdater;
    private boolean cancelled = false;
    private Timer myTimer;

    public WarpTimerImpl(WarpUpdater warpUpdater) {
        this.warpUpdater = warpUpdater;
        this.myTimer = createTimer(warpUpdater);
    }

    @Override
    public void doNextWarp(WarpUpdater warpUpdater) {
        warpUpdater.doNextWarp();
    }

    @Override
    public void cancel() {
        myTimer.cancel();
        cancelled = true;
    }

    @Override
    public void scheduleRepeating(int periodMillis) {
        if (cancelled) {
            cancelled = false;
            this.myTimer = createTimer(warpUpdater);
        }
        myTimer.scheduleRepeating(periodMillis);

    }

    private Timer createTimer(WarpUpdater warpUpdater) {
        return new MyTimer(warpUpdater);
    }

    private static class MyTimer extends Timer {


        private final WarpUpdater warpUpdater;

        MyTimer(WarpUpdater warpUpdater) {
            this.warpUpdater = warpUpdater;
        }

        @Override
        public void run() {
            warpUpdater.doNextWarp();
        }
    }
}
