package de.trundicho.warp.reader.view.ui;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import de.trundicho.warp.reader.core.controller.position.ReadingPositionPlayModelUpdater;
import de.trundicho.warp.reader.core.view.api.widgets.ReadingPositionBox;
import org.vaadin.addon.sliders.PaperSlider;

class ReadingPositionBoxImpl implements ReadingPositionBox {
    private final PaperSlider readPositionBox;

    ReadingPositionBoxImpl(PaperSlider readPositionBox) {
        this.readPositionBox = readPositionBox;
    }

    @Override
    public Integer getReadPositionPercentage() {
        return readPositionBox.getValue();
    }

    @Override
    public void setReadPositionPercentage(Integer readPosition) {
        readPositionBox.setValue(readPosition);
    }

    @Override
    public void registerChangeListenerAction(ReadingPositionPlayModelUpdater action) {
        readPositionBox.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<PaperSlider, Integer>>) paperSliderIntegerComponentValueChangeEvent -> action.run());
    }

}
