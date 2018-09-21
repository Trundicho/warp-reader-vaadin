package de.trundicho.warp.reader.client.view.api.widgets;

import de.trundicho.warp.reader.client.controller.position.ReadingPositionPlayModelUpdater;

public interface ReadingPositionBox extends ListenerRegistrar<ReadingPositionPlayModelUpdater> {

	Integer getReadPositionPercentage();

	void setReadPositionPercentage(Integer readPosition);

	void setMaxLength(int maxLength);

}