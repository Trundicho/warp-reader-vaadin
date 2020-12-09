package de.trundicho.warp.reader.view.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewBuilder;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModel;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModelImpl;
import de.trundicho.warp.reader.core.view.api.WarpReaderViewModelMutable;
import de.trundicho.warp.reader.core.view.api.widgets.WarpTextWidget;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;
import org.vaadin.addon.sliders.PaperSlider;

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
		warpPanel.add(leftWarpPanel);
        Label rightWarpPanel = warpTextPanelModel.getRightWarpPanel();
		warpPanel.add(rightWarpPanel);
		warpPanel.setAlignSelf(FlexComponent.Alignment.END, leftWarpPanel);
		warpPanel.setAlignSelf(FlexComponent.Alignment.START, rightWarpPanel);

		verticalPanel.add(warpPanel);
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.add(playButton);
		horizontalPanel.add(inputTextArea);

		final PaperSlider wpmSlider = createWpmSlider();
        final TextField wpmBox = createWpmBox();

        WordsPerMinuteWidget wordsPerMinute = new WordsPerMinuteBoxImpl(wpmSlider, wpmBox);
		uiModel.setWordsPerMinuteBox(wordsPerMinute);
        HorizontalLayout tempoPanel = new HorizontalLayout();

		HorizontalLayout wpmBoxPanel = createWpmSpinner(wpmSlider, wpmBox);
        tempoPanel.add(wpmBoxPanel, durationLabel);
        final HorizontalLayout positionComponents = createPercantageSpinner(uiModel);
		horizontalPanel.add(buttonPanel);
		verticalPanel.add(horizontalPanel, positionComponents, tempoPanel);

		ui.add(verticalPanel);

		inputTextArea.focus();
		inputTextArea.setAutoselect(true);

		return uiModel;
	}

	private HorizontalLayout createWpmSpinner(Component wpmSlider, Component wpmBox) {
		HorizontalLayout horizontalPanel = new HorizontalLayout();
		horizontalPanel.add(wpmSlider, wpmBox, new Label("Speed"));
		return horizontalPanel;
	}

	private PaperSlider createWpmSlider() {
        final PaperSlider wpmSlider = new PaperSlider(1, 1500, 1);
        wpmSlider.getElement().getStyle().set("width", "200px");
		wpmSlider.setId("numberSlider");
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
		PaperSlider slider = new PaperSlider(0, 100, 80);
		slider.getElement().getStyle().set("width", "200px");
        slider.setId("percentageSpinner");
//        slider.addStyleName(ValoTheme.PROGRESSBAR_POINT);
//        slider.setOrientation(SliderOrientation.HORIZONTAL);
		uiModel.setReadPosition(new ReadingPositionBoxImpl(slider));
		horizontalPanel.add(slider, new Label("Position %"));
		return horizontalPanel;
	}

	private Button createPlayButton() {
		Button playButton = new Button();
		playButton.getClassNames().add("playButton");
        final String size = "100px";
        playButton.setWidth(size);
        playButton.setHeight(size);
		return playButton;
	}
}