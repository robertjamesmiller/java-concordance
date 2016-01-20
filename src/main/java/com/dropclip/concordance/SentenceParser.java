package com.dropclip.concordance;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {
	private static final Pattern SENTENCE_PATTERN = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)");
	private String paragraph;

	public SentenceParser(String paragraph) {
		this.paragraph = paragraph;
	}
    /*
     * @TODO: should Pattern.MULTILINE be used?
     */
	public List<String> getSentences() {
		List<String> sentences = new ArrayList<String>();
		if (this.paragraph != null){
			Matcher m = SENTENCE_PATTERN.matcher(this.paragraph);
			while (m.find()) {
				sentences.add(m.group());
			}
		}
		return sentences;
	}

}
