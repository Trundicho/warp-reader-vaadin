package de.trundicho.warp.reader.client.model.warpword;

import de.trundicho.warp.reader.client.model.warpword.impl.WarpWordModel;

/**
 * Created by angeloromito on 11.04.14.
 */
public final class WarpWordBuilder {

	private final WarpWordModel EMPTY = new WarpWordModel("", "", "");

	public WarpWordModel buildWarpWordModel(String currentWordToDisplay) {
		if (currentWordToDisplay == null || currentWordToDisplay.isEmpty()) {
			return EMPTY;
		}
		int length2 = currentWordToDisplay.length();
		boolean numberOfCharsIsEven = length2 % 2 == 0;
		int middleCharVariant = 0;
		if (numberOfCharsIsEven) {
			middleCharVariant = 1;
		}
		int middle = (length2 / 2) - middleCharVariant;
		WarpWordModel warpWordModel = null;
		if (middle >= 0) {
			char charAt = currentWordToDisplay.charAt(middle);
			if (Character.isSpace(charAt)) {
				if (middleCharVariant == 0) {
					middle = middle - 1;
				} else {
					middle = middle + 1;
				}
			}
			CharSequence preSequence = currentWordToDisplay.subSequence(0, middle);
			String leftPart = preSequence.toString();
			char charAtMiddle = currentWordToDisplay.charAt(middle);
			String centerPart = String.valueOf(charAtMiddle);
			CharSequence postSequence = currentWordToDisplay.subSequence(middle + 1, length2);
			String rightPart = postSequence.toString();
			warpWordModel = new WarpWordModel(leftPart, centerPart, rightPart);
		}
		return warpWordModel;
	}

}
