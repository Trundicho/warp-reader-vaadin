package de.trundicho.warp.reader.core.controller;

import java.util.ArrayList;
import java.util.List;

public final class Disposer {

	private final List<Runnable> disposables = new ArrayList<>();

	public void add(Runnable disposeRunnable) {
		disposables.add(disposeRunnable);
	}

	public void doDispose() {
		for (Runnable runnable : disposables) {
			runnable.run();
		}
		disposables.clear();
	}
}