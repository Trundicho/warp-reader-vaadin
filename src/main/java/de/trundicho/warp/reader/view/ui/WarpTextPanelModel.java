package de.trundicho.warp.reader.view.ui;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

final class WarpTextPanelModel {
    private HorizontalLayout mainPanel;
    private Label leftWarpPanel;
    private Label rightWarpPanel;

    WarpTextPanelModel() {
        this.mainPanel = new HorizontalLayout();
        mainPanel.setMargin(false);
        mainPanel.setMargin(false);
        final ContentMode contentMode = ContentMode.HTML;
        leftWarpPanel = new Label("", contentMode);
        rightWarpPanel = new Label("", contentMode);
        mainPanel.setPrimaryStyleName("warpRegion");
        leftWarpPanel.setPrimaryStyleName("leftWarpPanel");
        rightWarpPanel.setPrimaryStyleName("rightWarpPanel");
    }

    public HorizontalLayout getMainPanel() {
        return mainPanel;
    }

    public Label getLeftWarpPanel() {
        return leftWarpPanel;
    }

    public Label getRightWarpPanel() {
        return rightWarpPanel;
    }

}