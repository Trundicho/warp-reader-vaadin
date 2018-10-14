package de.trundicho.warp.reader.core;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;

import java.util.Timer;
import java.util.TimerTask;

class WarpTimerForTest implements WarpTimer {
    private Timer timer;
    private final Runnable runnable;

    WarpTimerForTest(WarpUpdater warpUpdater) {
        timer = new Timer("Timer");
        runnable = new Runnable() {

            @Override
            public void run() {
                warpUpdater.doNextWarp();
            }
        };

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
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.schedule(timerTask, periodMillis);
    }
}
