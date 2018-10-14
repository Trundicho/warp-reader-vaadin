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
        final String text = leftPart + centerPart + rightPart;
        warpedTextLines.add(text);
        System.err.println(text);
    }

    /**
     * For test only
     */
    public List<String> getWarpedTextLines() {
        return warpedTextLines;
    }
}
