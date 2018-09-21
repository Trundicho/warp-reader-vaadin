package de.trundicho.warp.reader.client.model.playmode;

/**
 * Created by angeloromito on 14.04.14.
 */
public enum PlayState {
	PLAYING("||"), PAUSE(">");

	private String sign;

	PlayState(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}
}
