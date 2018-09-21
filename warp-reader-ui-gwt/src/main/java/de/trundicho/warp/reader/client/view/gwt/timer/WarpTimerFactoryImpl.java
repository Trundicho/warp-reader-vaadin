package de.trundicho.warp.reader.client.view.gwt.timer;

import de.trundicho.warp.reader.client.model.playmode.impl.PlayModel;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimer;
import de.trundicho.warp.reader.client.view.api.timer.WarpTimerFactory;

public class WarpTimerFactoryImpl implements WarpTimerFactory {

	private final PlayModel playModel;

	public WarpTimerFactoryImpl(PlayModel playModel) {
		this.playModel = playModel;
	}

	@Override
	public WarpTimer createWarpTimer() {
		return new WarpTimerImpl(playModel);
	}

}
