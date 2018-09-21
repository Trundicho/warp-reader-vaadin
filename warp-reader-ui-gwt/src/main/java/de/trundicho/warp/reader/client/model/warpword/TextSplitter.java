package de.trundicho.warp.reader.client.model.warpword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by angeloromito on 13.04.14.
 */
public final class TextSplitter {
	private final WordLengthModel wordLengthModel;
	private int numberOfWords;

	public TextSplitter(WordLengthModel wLModel) {
		this.wordLengthModel = wLModel;

	}

	public String[] splitText(String text) {
		String reg = "[\\s]+";
		String[] split = text.split(reg);
		numberOfWords = split.length;

		ArrayList<String> arrayList = new ArrayList<String>();
		for (String string : split) {
			String trim = string.trim();
			int length = trim.length();
			if (length > wordLengthModel.getMax()) {
				arrayList.add(trim.substring(0, 1 + length / 2) + "-");
				arrayList.add(trim.substring(1 + length / 2, length));
			} else {
				arrayList.add(trim);
			}
		}
		return arrayList.toArray(new String[arrayList.size()]);
	}

	public String[] getCombined(String[] initialTextSplit) {
		final int max = wordLengthModel.getMax();
		final int min = wordLengthModel.getMin();
		final List<String> combined = new ArrayList<String>();
		String combinedText = "";
		for (final Iterator<String> iterator = Arrays.asList(initialTextSplit).iterator(); iterator.hasNext();) {
			final String textSegment = (String) iterator.next();
			final boolean hasNext = iterator.hasNext();
			String previous = combinedText;
			combinedText += combinedText.isEmpty() ? textSegment : " " + textSegment;
			final int combinedTextLength = combinedText.length();
			if (combinedTextLength >= min && combinedTextLength <= max) {
				add(combined, combinedText);
				combinedText = "";
			} else if (combinedTextLength < min) {
				if (!hasNext) {
					add(combined, combinedText);
				}
			} else {
				if (previous.length() <= max) {
					add(combined, previous);
				}
				combinedText = textSegment;
				if (combinedText.length() > max) {
					add(combined, combinedText);
				}
				if (!hasNext) {
					add(combined, combinedText);
				}
			}
		}
		return combined.toArray(new String[combined.size()]);
	}

	private void add(final List<String> combined, String combinedText) {
		if (combinedText.length() > 0 && !combinedText.equals(" ") && !combinedText.isEmpty()) {
			combined.add(combinedText);
		}
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}
}
