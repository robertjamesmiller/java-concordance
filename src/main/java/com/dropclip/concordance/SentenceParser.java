package com.dropclip.concordance;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SentenceParser {
	private String paragraph;

	public SentenceParser(String paragraph) {
		this.paragraph = paragraph;
	}

	public List<String> getSentences() {
		List<String> sentences = new ArrayList<String>();
		if (this.paragraph != null && this.paragraph.trim().length() > 0){
			BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
			sentenceIterator.setText(this.paragraph);
			int start = sentenceIterator.first();
			for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
				sentences.add(this.paragraph.substring(start,end).trim());
			}
		}
		return sentences;
	}

}
