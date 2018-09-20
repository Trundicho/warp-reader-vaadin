package de.trundicho.warp.reader.core.view.api.widgets;

import de.trundicho.warp.reader.core.controller.position.ReadingPositionPlayModelUpdater;

public interface ReadingPositionBox extends ListenerRegistrar<ReadingPositionPlayModelUpdater> {

	Integer getReadPositionPercentage();

	void setReadPositionPercentage(Integer readPosition);

}