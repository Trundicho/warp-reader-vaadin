package de.trundicho.warp.reader.core;

import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;

import java.util.ArrayList;
import java.util.List;

class WarpTextWidgetForTest implements WarpTextWidget {

    private final List<String> warpedTextLines;

    WarpTextWidgetForTest() {
        warpedTextLines = new ArrayList<>();
    }

    @Override
    public void setWarpText(String leftPart, String centerPart, String rightPart) {
        warpedTextLines.add(leftPart + centerPart + rightPart);
    }

    /**
     * For test only
     */
    public List<String> getWarpedTextLines() {
        return warpedTextLines;
    }
}
