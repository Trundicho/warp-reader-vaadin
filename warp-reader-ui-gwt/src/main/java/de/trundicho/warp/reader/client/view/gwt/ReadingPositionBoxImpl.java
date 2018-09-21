package de.trundicho.warp.reader.client.view.gwt;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.IntegerBox;

import de.trundicho.warp.reader.client.controller.position.ReadingPositionPlayModelUpdater;
import de.trundicho.warp.reader.client.view.api.widgets.ReadingPositionBox;

class ReadingPositionBoxImpl implements ReadingPositionBox {
	private IntegerBox readPositionBox;

	ReadingPositionBoxImpl(IntegerBox readPositionBox) {
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
	public void setMaxLength(int maxLength) {
		readPositionBox.setMaxLength(maxLength);
	}

	@Override
	public void registerChangeListenerAction(ReadingPositionPlayModelUpdater action) {
		readPositionBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				action.run();
			}
		});
	}

}
