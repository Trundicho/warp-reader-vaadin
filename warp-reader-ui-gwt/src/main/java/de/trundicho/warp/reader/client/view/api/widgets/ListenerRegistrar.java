package de.trundicho.warp.reader.client.view.api.widgets;

public interface ListenerRegistrar<ACTION_RUNNER extends Runnable> {
	void registerChangeListenerAction(ACTION_RUNNER actionRunner);
}
