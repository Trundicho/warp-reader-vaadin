package de.trundicho.warp.reader.core.model.playmode.impl;

import java.util.ArrayList;
import java.util.Collection;

public final class PlayModel {
    private int index = 0;
    private String[] split;

    public interface Listener {

        void positionUpdate(int currentPositionPercentage, String word);

    }

    private final Collection<Listener> listeners = new ArrayList<>();

    public String nextIncrement() {
        if (index < split.length) {
            index += 1;
            if (index < split.length) {
                for (Listener listener : listeners) {
                    listener.positionUpdate(getCurrentPositionPercentage(), split[index]);
                }
                return split[index];
            }
        }
        return null;
    }

    public int getCurrentPositionPercentage() {
        return split.length == 0 ? 0 : 100 * index / split.length;
    }

    public void setCurrentPosition(int percentage) {
        index = (int) Math.ceil((percentage / 100.0) * split.length);
        updateListeners();
    }

    private void updateListeners() {
        final int currentPositionPercentage = getCurrentPositionPercentage();
        if (index < 0) {
            index = 0;
        }
        if (index > split.length - 1) {
            index = split.length - 1;
        }
        final String word = split.length == 0 ? "" : split[index];
        for (Listener listener : listeners) {
            listener.positionUpdate(currentPositionPercentage, word);
        }
    }

    public void init(String[] split) {
        index = 0;
        this.split = split;
        updateListeners();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        if (listener != null && listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

}