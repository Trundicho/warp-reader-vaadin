package de.trundicho.warp.reader.view.ui;

import com.vaadin.flow.component.html.Label;
import de.trundicho.warp.reader.core.view.api.widgets.NumberLabelWidget;

public class DurationLabelImpl implements NumberLabelWidget {

	private final Label durationLabel;

	public DurationLabelImpl(Label durationLabel) {
		this.durationLabel = durationLabel;
	}

	public void updateNumberLabel(int overallDurationMs) {
		int seconds = (overallDurationMs / 1000) % 60;
		long minutes = ((overallDurationMs / 1000) / 60);// % 60;
		int hours = (((overallDurationMs / 1000) / 60) / 60);
		durationLabel.setText(minutes + "Min " + seconds + "Sec");
	}
}