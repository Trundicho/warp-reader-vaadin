package de.trundicho.warp.reader.view.ui;


import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

final class WarpTextPanelModel {
    private HorizontalLayout mainPanel;
    private Label leftWarpPanel;
    private Label rightWarpPanel;

    WarpTextPanelModel() {
        this.mainPanel = new HorizontalLayout();
        mainPanel.setMargin(false);
        mainPanel.setMargin(false);
        leftWarpPanel = new Label("");
        rightWarpPanel = new Label("");
        mainPanel.getClassNames().add("warpRegion");
        leftWarpPanel.getClassNames().add("leftWarpPanel");
        rightWarpPanel.getClassNames().add("rightWarpPanel");
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