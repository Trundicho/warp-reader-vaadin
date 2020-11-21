package de.trundicho.warp.reader.core.view.api.widgets;

import de.trundicho.warp.reader.core.model.playmode.PlayState;

public interface PlayButtonWidget extends ButtonWidget {
    void updateWidgetStyle(PlayState playState);
}