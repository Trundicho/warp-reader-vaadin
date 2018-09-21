package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

final class WarpTextPanelModel {
	private HorizontalPanel mainPanel;
	private SimplePanel leftWarpPanel;
	private SimplePanel rightWarpPanel;

	WarpTextPanelModel() {
		this.mainPanel = new HorizontalPanel();
		leftWarpPanel = new SimplePanel();
		rightWarpPanel = new SimplePanel();
		mainPanel.setStyleName("warpRegion");
		leftWarpPanel.setStyleName("leftWarpPanel");
		rightWarpPanel.setStyleName("rightWarpPanel");
	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

	public SimplePanel getLeftWarpPanel() {
		return leftWarpPanel;
	}

	public SimplePanel getRightWarpPanel() {
		return rightWarpPanel;
	}

}