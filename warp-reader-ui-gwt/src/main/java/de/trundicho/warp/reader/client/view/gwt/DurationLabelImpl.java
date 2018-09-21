package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.ui.Label;
import de.trundicho.warp.reader.core.view.api.widgets.DurationWidget;

public class DurationLabelImpl implements DurationWidget {

	private Label durationLabel;

	public DurationLabelImpl(Label durationLabel) {
		this.durationLabel = durationLabel;
	}

	public void updateDurationLabel(int overallDurationMs) {
		int seconds = (overallDurationMs / 1000) % 60;
		long minutes = ((overallDurationMs / 1000) / 60);// % 60;
		int hours = (((overallDurationMs / 1000) / 60) / 60);
		durationLabel.setText(minutes + "Min " + seconds + "Sec");
	}
}