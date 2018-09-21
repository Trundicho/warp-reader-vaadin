package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;

class WarpTextLabelUpdaterImpl implements WarpTextWidget {

	private WarpTextPanelModel warpTextLabel;

	WarpTextLabelUpdaterImpl(WarpTextPanelModel warpTextLabel) {
		this.warpTextLabel = warpTextLabel;
	}

	public void setWarpText(String leftPart, String centerPart, String rightPart) {
		SimplePanel leftPanel = warpTextLabel.getLeftWarpPanel();
		Element leftElement = leftPanel.getElement();
		SimplePanel rightPanel = warpTextLabel.getRightWarpPanel();
		Element rightElement = rightPanel.getElement();
		leftElement.setInnerHTML(leftPart.replaceAll(" ", "&nbsp;"));
		String secondPart = "<span class='middleLetter'>" + centerPart + "</span>"
				+ rightPart.replaceAll(" ", "&nbsp;");
		rightElement.setInnerHTML(secondPart);
	}
}