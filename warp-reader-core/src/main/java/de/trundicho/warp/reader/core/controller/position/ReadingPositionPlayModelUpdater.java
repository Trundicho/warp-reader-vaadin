package de.trundicho.warp.reader.core.controller.position;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;
import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.view.api.widgets.ReadingPositionBox;

public class ReadingPositionPlayModelUpdater implements Runnable {
    private final ReadingPositionBox wordLengthBox;
    private final PlayModel playModel;
    private final PlayModeModel playModeModel;

    public ReadingPositionPlayModelUpdater(ReadingPositionBox wordLengthBox,
                                           PlayModel playModel,
                                           PlayModeModel playModeModel) {
        this.wordLengthBox = wordLengthBox;
        this.playModel = playModel;
        this.playModeModel = playModeModel;
    }

    @Override
    public void run() {
        final PlayState playState = playModeModel.getPlayState();
        final boolean isRunning = PlayState.PLAYING.equals(playState);
        if (isRunning) {
            playModeModel.setPlayState(PlayState.PAUSE);
        }
        Integer value = wordLengthBox.getReadPositionPercentage();
        playModel.setCurrentPosition(value);
        if (isRunning) {
            playModeModel.setPlayState(PlayState.PLAYING);
        }
    }

}