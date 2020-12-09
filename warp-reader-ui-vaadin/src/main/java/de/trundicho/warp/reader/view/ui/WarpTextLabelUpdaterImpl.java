package de.trundicho.warp.reader.view.ui;

import com.vaadin.flow.component.html.Label;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;

class WarpTextLabelUpdaterImpl implements WarpTextWidget {

    private final WarpTextPanelModel warpTextLabel;

    WarpTextLabelUpdaterImpl(WarpTextPanelModel warpTextLabel) {
        this.warpTextLabel = warpTextLabel;
    }

    public void setWarpText(String leftPart, String centerPart, String rightPart) {
        Label leftPanel = warpTextLabel.getLeftWarpPanel();
        leftPanel.getElement().setProperty("innerHTML", leftPart.replaceAll(" ", "&nbsp;"));
        String secondPart = "<span class='middleLetter'>" + centerPart + "</span>"
                + rightPart.replaceAll(" ", "&nbsp;");
        Label rightPanel = warpTextLabel.getRightWarpPanel();
        rightPanel.getElement().setProperty("innerHTML", secondPart);
    }

}