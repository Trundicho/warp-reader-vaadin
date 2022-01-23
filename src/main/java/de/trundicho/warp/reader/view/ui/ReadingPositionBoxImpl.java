package de.trundicho.warp.reader.view.ui;

import de.trundicho.warp.reader.core.controller.position.ReadingPositionPlayModelUpdater;
import de.trundicho.warp.reader.core.view.api.widgets.ReadingPositionBox;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Slider;

class ReadingPositionBoxImpl implements ReadingPositionBox {
    private Slider readPositionBox;

    ReadingPositionBoxImpl(Slider readPositionBox) {
        this.readPositionBox = readPositionBox;
    }

    @Override
    public Integer getReadPositionPercentage() {
        return readPositionBox.getValue().intValue();
    }

    @Override
    public void setReadPositionPercentage(Integer readPosition) {
        readPositionBox.setValue((double) readPosition);
    }

    @Override
    public void registerChangeListenerAction(ReadingPositionPlayModelUpdater action) {
        readPositionBox.addValueChangeListener((HasValue.ValueChangeListener<Double>) valueChangeEvent -> action.run());
    }

}
