package de.trundicho.warp.reader.core.controller;

import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;

public class WarpUpdater {

    private final PlayModel playModel;

    public WarpUpdater(PlayModel playModel) {
        this.playModel = playModel;
    }

    public void doNextWarp(){
        playModel.nextIncrement();
    }
}
