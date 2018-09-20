package de.trundicho.warp.reader.core.view.api.timer;

public interface WarpReaderTimer {

	void cancel();

	void scheduleRepeating(int periodMillis);
}
