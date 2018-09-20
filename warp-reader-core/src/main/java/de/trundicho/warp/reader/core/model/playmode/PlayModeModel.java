package de.trundicho.warp.reader.core.model.playmode;

/**
 * Created by angeloromito on 14.04.14.
 */
public interface PlayModeModel {
	interface Listener {
		void playStateChanged(PlayState playState);
	}

	PlayState getPlayState();

	void setPlayState(PlayState playState);

	void addListener(Listener listener);

	void removeListener(Listener listener);

	void dispose();
}
