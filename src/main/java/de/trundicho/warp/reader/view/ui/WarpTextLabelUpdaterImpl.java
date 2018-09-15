package de.trundicho.warp.reader.view.ui;

import com.vaadin.ui.Label;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;

class WarpTextLabelUpdaterImpl implements WarpTextWidget {

    private WarpTextPanelModel warpTextLabel;

    WarpTextLabelUpdaterImpl(WarpTextPanelModel warpTextLabel) {
        this.warpTextLabel = warpTextLabel;
    }

    public void setWarpText(String leftPart, String centerPart, String rightPart) {
        Label leftPanel = warpTextLabel.getLeftWarpPanel();
        leftPanel.setValue(leftPart.replaceAll(" ", "&nbsp;"));
        String secondPart = "<span class='middleLetter'>" + centerPart + "</span>"
                + rightPart.replaceAll(" ", "&nbsp;");
        Label rightPanel = warpTextLabel.getRightWarpPanel();
        rightPanel.setValue(secondPart);
    }

}