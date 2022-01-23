package de.trundicho.warp.reader.view.ui;

import de.trundicho.warp.reader.core.view.api.WarpReaderViewBuilder;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModel;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModelImpl;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModelMutable;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;

import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class WarpReaderViewBuilderImpl implements WarpReaderViewBuilder {

	private final UI ui;

	public WarpReaderViewBuilderImpl(UI ui){
		this.ui = ui;
	}

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
		inputTextArea.setId("textArea");
		return inputTextArea;
	}

	private WarpReaderViewModel buildUILayout(final TextArea inputTextArea, WarpTextPanelModel warpTextPanelModel,
			Button playButton, Label durationLabel, WarpReaderViewModelMutable uiModel) {
		VerticalLayout verticalPanel = new VerticalLayout();
		verticalPanel.setId("mainContentTable");

		HorizontalLayout horizontalPanel = new HorizontalLayout();
		HorizontalLayout warpPanel = warpTextPanelModel.getMainPanel();
		Label leftWarpPanel = warpTextPanelModel.getLeftWarpPanel();
		warpPanel.addComponent(leftWarpPanel);
        Label rightWarpPanel = warpTextPanelModel.getRightWarpPanel();
		warpPanel.addComponent(rightWarpPanel);
		warpPanel.setComponentAlignment(leftWarpPanel, Alignment.MIDDLE_RIGHT);
		warpPanel.setComponentAlignment(rightWarpPanel, Alignment.MIDDLE_LEFT);

		verticalPanel.addComponent(warpPanel);
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.addComponent(playButton);
		horizontalPanel.addComponent(inputTextArea);

		final Slider wpmSlider = createWpmSlider();
        final TextField wpmBox = createWpmBox();

        WordsPerMinuteWidget wordsPerMinute = new WordsPerMinuteBoxImpl(wpmSlider, wpmBox);
		uiModel.setWordsPerMinuteBox(wordsPerMinute);
        HorizontalLayout tempoPanel = new HorizontalLayout();

		HorizontalLayout wpmBoxPanel = createWpmSpinner(wpmSlider, wpmBox);
        tempoPanel.addComponent(wpmBoxPanel);
        tempoPanel.addComponent(durationLabel);
        final HorizontalLayout positionComponents = createPercantageSpinner(uiModel);
		horizontalPanel.addComponent(buttonPanel);
		verticalPanel.addComponent(horizontalPanel);
        verticalPanel.addComponent(positionComponents);
        verticalPanel.addComponent(tempoPanel);

		ui.setContent(verticalPanel);

		inputTextArea.focus();
		inputTextArea.selectAll();

		return uiModel;
	}

	private HorizontalLayout createWpmSpinner(Component wpmSlider, Component wpmBox) {
		HorizontalLayout horizontalPanel = new HorizontalLayout();
		horizontalPanel.addComponent(wpmSlider);
		horizontalPanel.addComponent(wpmBox);
		horizontalPanel.addComponent(new Label("Speed"));
		return horizontalPanel;
	}

	private Slider createWpmSlider() {
        final Slider wpmSlider = new Slider(1, 1500);
        wpmSlider.setWidth("200px");
		wpmSlider.setId("numberSlider");
		wpmSlider.setOrientation(SliderOrientation.HORIZONTAL);
		return wpmSlider;
	}

    private TextField createWpmBox() {
        final TextField wpmSlider = new TextField();
        wpmSlider.setId("numberSpinner");
        wpmSlider.setWidth("60px");
        return wpmSlider;
    }

	private HorizontalLayout createPercantageSpinner(WarpReaderViewModelMutable uiModel) {
		HorizontalLayout horizontalPanel = new HorizontalLayout();
		Slider slider = new Slider(0, 100);
        slider.setWidth("200px");
        slider.setId("percentageSpinner");
        slider.addStyleName(ValoTheme.PROGRESSBAR_POINT);
        slider.setOrientation(SliderOrientation.HORIZONTAL);
		uiModel.setReadPosition(new ReadingPositionBoxImpl(slider));
		horizontalPanel.addComponent(slider);
		horizontalPanel.addComponent(new Label("Position %"));
		return horizontalPanel;
	}

	private Button createPlayButton() {
		Button playButton = new Button();
		playButton.addStyleName("playButton");
        final String size = "100px";
        playButton.setWidth(size);
        playButton.setHeight(size);
		return playButton;
	}
}