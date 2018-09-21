package de.trundicho.warp.reader.client.model.warpword.impl;

public class WarpWordModel {

	private String leftPart;
	private String centerPart;
	private String rightPart;

	public WarpWordModel(String leftPart, String centerPart, String rightPart) {
		this.leftPart = leftPart;
		this.centerPart = centerPart;
		this.rightPart = rightPart;
	}

	public String getLeftPart() {
		return leftPart;
	}

	public String getCenterPart() {
		return centerPart;
	}

	public String getRightPart() {
		return rightPart;
	}

}
