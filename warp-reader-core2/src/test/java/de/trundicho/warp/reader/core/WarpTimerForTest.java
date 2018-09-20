package de.trundicho.warp.reader.core;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class WarpTimerForTest implements WarpTimer {
    private final WarpUpdater warpUpdater;
    private final ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> schedule;

    WarpTimerForTest(WarpUpdater warpUpdater) {
        this.warpUpdater = warpUpdater;
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void doNextWarp(WarpUpdater warpUpdater) {
        warpUpdater.doNextWarp();
    }

    @Override
    public void cancel() {
        schedule.cancel(false);
    }

    @Override
    public void scheduleRepeating(int periodMillis) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                doNextWarp(warpUpdater);
            }
        };
        schedule = scheduledExecutorService.schedule(task, periodMillis, TimeUnit.MILLISECONDS);
    }
}
