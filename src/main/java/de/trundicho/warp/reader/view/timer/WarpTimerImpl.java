package de.trundicho.warp.reader.view.timer;

import com.vaadin.ui.Timer;
import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.view.WarpReaderView;

class WarpTimerImpl implements WarpTimer {
    private final PlayModel playModel;
    private final Timer timer;

    WarpTimerImpl(PlayModel playModel, WarpReaderView ui) {
        this.playModel = playModel;
        timer = new Timer();
        timer.run(() -> doNextWarp(playModel));
        ui.addExtension(timer);
    }

    @Override
    public void doNextWarp(PlayModel playModel) {
        playModel.nextIncrement();
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
