package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.trundicho.warp.reader.client.view.api.WarpReaderViewBuilder;
import de.trundicho.warp.reader.client.view.api.WarpReaderViewModel;
import de.trundicho.warp.reader.client.view.api.WarpReaderViewModelImpl;
import de.trundicho.warp.reader.client.view.api.WarpReaderViewModelMutable;
import de.trundicho.warp.reader.client.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.client.view.api.widgets.WordsPerMinuteWidget;

public class WarpReaderViewBuilderImpl implements WarpReaderViewBuilder {

	@Override
	public WarpReaderViewModel buildView() {
		WarpReaderViewModelMutable uiModel = new WarpReaderViewModelImpl();
		final TextArea inputTextArea = createInputTextArea();
		uiModel.setInputTextArea(new InputTextAreaImpl(inputTextArea));
		Button playButton = createPlayButton();
		Label durationLabel = new Label();
		uiModel.setDurationLabel(new DurationLabelImpl(durationLabel));
		WarpTextPanelModel warpTextPanelModel = new WarpTextPanelModel();

		uiModel.setPlayButton(new PlayButtonWidgetImpl(playButton));
		WarpTextWidget warpTextLabelUpdater = new WarpTextLabelUpdaterImpl(warpTextPanelModel);
		uiModel.setWarpTextLabelUpdater(warpTextLabelUpdater);
		return buildUILayout(inputTextArea, warpTextPanelModel, playButton, durationLabel, uiModel);
	}

	private TextArea createInputTextArea() {
		final TextArea inputTextArea = new TextArea();
		inputTextArea.getElement().setId("textArea");
		return inputTextArea;
	}

	private WarpReaderViewModel buildUILayout(final TextArea inputTextArea, WarpTextPanelModel warpTextPanelModel,
			Button playButton, Label durationLabel, WarpReaderViewModelMutable uiModel) {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.getElement().setId("mainContentTable");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		HorizontalPanel warpPanel = warpTextPanelModel.getMainPanel();
		SimplePanel leftWarpPanel = warpTextPanelModel.getLeftWarpPanel();
		warpPanel.add(leftWarpPanel);
		SimplePanel rightWarpPanel = warpTextPanelModel.getRightWarpPanel();
		warpPanel.add(rightWarpPanel);
		warpPanel.setCellHorizontalAlignment(leftWarpPanel, HorizontalPanel.ALIGN_RIGHT);
		warpPanel.setCellHorizontalAlignment(rightWarpPanel, HorizontalPanel.ALIGN_LEFT);
		warpPanel.setWidth("100%");

		verticalPanel.add(warpPanel);
		VerticalPanel controlPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(playButton);
		horizontalPanel.add(inputTextArea);
		controlPanel.add(buttonPanel);

		final IntegerBox wpmBox = createWpmBox();
		WordsPerMinuteWidget wordsPerMinute = new WordsPerMinuteBoxImpl(wpmBox);
		uiModel.setWordsPerMinuteBox(wordsPerMinute);

		Panel wpmBoxPanel = createWpmSpinner(wpmBox);
		controlPanel.add(wpmBoxPanel);
		controlPanel.add(createPercantageSpinner(uiModel));
		controlPanel.add(durationLabel);
		horizontalPanel.add(controlPanel);
		verticalPanel.add(horizontalPanel);

		RootPanel.get().add(verticalPanel);

		inputTextArea.setFocus(true);
		inputTextArea.selectAll();

		return uiModel;
	}

	private Panel createWpmSpinner(IntegerBox wpmBox) {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(wpmBox);
		horizontalPanel.add(new Label("UPM"));
		return horizontalPanel;
	}

	private IntegerBox createWpmBox() {
		final IntegerBox wpmBox = new IntegerBox();
		Element wpmBoxElement = wpmBox.getElement();
		wpmBoxElement.setId("numberSpinner");
		wpmBoxElement.setPropertyString("type", "number");
		return wpmBox;
	}

	private Panel createPercantageSpinner(WarpReaderViewModelMutable uiModel) {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		final IntegerBox wordLengthBox = new IntegerBox();
		Element wpmBoxElement = wordLengthBox.getElement();
		wpmBoxElement.setId("percentageSpinner");
		wpmBoxElement.setPropertyString("type", "number");
		uiModel.setReadPosition(new ReadingPositionBoxImpl(wordLengthBox));
		horizontalPanel.add(wordLengthBox);
		horizontalPanel.add(new Label("Position %"));
		return horizontalPanel;
	}

	private Button createPlayButton() {
		Button playButton = new Button();
		playButton.addStyleName("playButton");
		return playButton;
	}
}