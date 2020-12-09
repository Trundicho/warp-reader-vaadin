package de.trundicho.warp.reader.view.ui;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.TextField;
import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;
import org.vaadin.addon.sliders.PaperSlider;

class WordsPerMinuteBoxImpl implements WordsPerMinuteWidget {
    private final PaperSlider wordsPerMinuteSlider;
    private final TextField wordsPerMinuteBox;

    WordsPerMinuteBoxImpl(PaperSlider wordsPerMinuteSlider, TextField wpmBox) {
        this.wordsPerMinuteSlider = wordsPerMinuteSlider;
        this.wordsPerMinuteBox = wpmBox;
    }

    @Override
    public Integer getWordsPerMinute() {
        return wordsPerMinuteSlider.getValue();
    }

    @Override
    public void setWordsPerMinute(Integer wordsPerMinute) {
        wordsPerMinuteSlider.setValue(wordsPerMinute);
        wordsPerMinuteBox.setValue(wordsPerMinute.toString());
    }

    @Override
    public void registerChangeListenerAction(WpmBoxSpeedModelUpdater action) {
        wordsPerMinuteSlider.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<PaperSlider, Integer>>) paperSliderIntegerComponentValueChangeEvent -> {
            Integer newValue = paperSliderIntegerComponentValueChangeEvent.getValue();
            if (!newValue.equals(paperSliderIntegerComponentValueChangeEvent.getOldValue())) {
                action.run();
                wordsPerMinuteBox.setValue(newValue + "");
            }
        });
        wordsPerMinuteBox.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<TextField, String>>) valueChangeEvent -> {
            final String newValue = valueChangeEvent.getValue();
            if (!newValue.equals(valueChangeEvent.getOldValue())) {
                action.run();
                wordsPerMinuteSlider.setValue(Integer.valueOf(newValue));
            }
        });
    }

}
