package de.trundicho.warp.reader.view.ui;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import de.trundicho.warp.reader.core.controller.speed.WpmBoxSpeedModelUpdater;
import de.trundicho.warp.reader.core.view.api.widgets.WordsPerMinuteWidget;

class WordsPerMinuteBoxImpl implements WordsPerMinuteWidget {
    private final Slider wordsPerMinuteSlider;
    private final TextField wordsPerMinuteBox;

    WordsPerMinuteBoxImpl(Slider wordsPerMinuteSlider, TextField wpmBox) {
        this.wordsPerMinuteSlider = wordsPerMinuteSlider;
        this.wordsPerMinuteBox = wpmBox;
    }

    @Override
    public Integer getWordsPerMinute() {
        return wordsPerMinuteSlider.getValue().intValue();
    }

    @Override
    public void setWordsPerMinute(Integer wordsPerMinute) {
        wordsPerMinuteSlider.setValue(wordsPerMinute.doubleValue());
        wordsPerMinuteBox.setValue(wordsPerMinute.toString());
    }

    @Override
    public void registerChangeListenerAction(WpmBoxSpeedModelUpdater action) {
        wordsPerMinuteSlider.addValueChangeListener(new HasValue.ValueChangeListener<Double>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<Double> valueChangeEvent) {
                final Double newValue = valueChangeEvent.getValue();
                if (!newValue.equals(valueChangeEvent.getOldValue())) {
                    action.run();
                    wordsPerMinuteBox.setValue(newValue.intValue() + "");
                }
            }
        });
        wordsPerMinuteBox.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                final String newValue = valueChangeEvent.getValue();
                if (!newValue.equals(valueChangeEvent.getOldValue())) {
                    action.run();
                    wordsPerMinuteSlider.setValue(Double.valueOf(newValue));
                }
            }
        });
    }

}
