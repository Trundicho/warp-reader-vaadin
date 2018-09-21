package de.trundicho.warp.reader.client.view.api.timer;

public interface Timer {

	void run();

	void cancel();

	void scheduleRepeating(int periodMillis);
}
