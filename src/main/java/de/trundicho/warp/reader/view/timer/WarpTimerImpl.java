package de.trundicho.warp.reader.view.timer;

import de.trundicho.warp.reader.core.controller.WarpUpdater;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.view.WarpReaderView;

import com.vaadin.ui.Timer;

public class WarpTimerImpl implements WarpTimer {
    private final WarpReaderView ui;
    private final WarpUpdater warpUpdater;
    private Timer timer;
    private boolean cancelled = false;

    public WarpTimerImpl(WarpUpdater warpUpdater, WarpReaderView ui) {
        this.ui = ui;
        this.warpUpdater = warpUpdater;
        timer = createTimer(warpUpdater, ui);

    }

    @Override
    public void doNextWarp(WarpUpdater warpUpdater) {
        warpUpdater.doNextWarp();
    }

    @Override
    public void cancel() {
        cancelled= true;
        timer.cancel();
    }

    @Override
    public void scheduleRepeating(int periodMillis) {
        if(cancelled){
            cancelled = false;
            timer= createTimer(warpUpdater, ui);
        }
        timer.schedule(periodMillis);

    }

    private Timer createTimer(WarpUpdater warpUpdater, WarpReaderView ui) {
        final Timer timer = new Timer();
        timer.run(() -> doNextWarp(warpUpdater));
        ui.addExtension(timer);
        return timer;
    }
}
