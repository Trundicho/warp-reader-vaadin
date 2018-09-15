package de.trundicho.warp.reader.view.timer;

import de.trundicho.warp.reader.core.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.core.view.api.timer.WarpTimerFactory;
import de.trundicho.warp.reader.view.WarpReaderView;

public class WarpTimerFactoryImpl implements WarpTimerFactory {

	private final PlayModel playModel;
	private final WarpReaderView ui;

	public WarpTimerFactoryImpl(PlayModel playModel, WarpReaderView ui) {
		this.playModel = playModel;
		this.ui = ui;
	}

	@Override
	public WarpTimer createWarpTimer() {
		return new WarpTimerImpl(playModel, ui);
	}

}
