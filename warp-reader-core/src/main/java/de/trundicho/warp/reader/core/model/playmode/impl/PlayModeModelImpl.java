package de.trundicho.warp.reader.core.model.playmode.impl;

import de.trundicho.warp.reader.core.model.playmode.PlayModeModel;
import de.trundicho.warp.reader.core.model.playmode.PlayState;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by angeloromito on 14.04.14.
 */
public final class PlayModeModelImpl implements PlayModeModel {
	private final Collection<Listener> listeners;
	private PlayState playState;

	public PlayModeModelImpl(final PlayState initialPlayState) {
		this.playState = initialPlayState;
		this.listeners = new HashSet<>();
	}

	@Override
	public PlayState getPlayState() {
		return playState;
	}

	@Override
	public void setPlayState(PlayState playState) {
		this.playState = playState;
		for (Listener listener : listeners) {
			listener.playStateChanged(playState);
		}
	}

	@Override
	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispose() {
		listeners.clear();
	}
}
