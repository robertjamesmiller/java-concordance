package com.dropclip.concordance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ConcordanceParser {
	private static final Pattern WORD_PATTERN = Pattern.compile("(\\w+\\.\\w+\\.|\\w+\\.\\w+)|\\w+-\\w+|\\w+");
	private String paragraph;

	public ConcordanceParser(String paragraph) {
		this.paragraph = paragraph;
	}
	 
	public List<String> getAlphabeticalListOfResults(){
		List<String> results = new ArrayList<String>();
		// A TreeMap's natural ordering is not alphabetical
		Map<String, ArrayList<String>> wordMap = new TreeMap<String, ArrayList<String>>(new Comparator<String>() {
		    public int compare(String o1, String o2) {
		        return o1.toLowerCase().compareTo(o2.toLowerCase());
		    }
		});
		// Parse the paragraph into sentences.
		SentenceParser sp = new SentenceParser(paragraph);
		int sentenceIndex = 0;
		for (String sentence : sp.getSentences()) {
			// Parse a sentence into words
			// @TODO: move word parsing into its own object with its own set of tests
			Matcher m = WORD_PATTERN.matcher(sentence);
			while (m.find()) {
				// lowercase all words
				String word = m.group().toLowerCase();
				if (wordMap.containsKey(word)){
					wordMap.get(word).add(""+sentenceIndex);
				} else {
					ArrayList<String> sentenceIndexList = new ArrayList<String>();
					sentenceIndexList.add(""+sentenceIndex);
					wordMap.put(word, sentenceIndexList);
				}
			}
			sentenceIndex++;
		}
		for(Map.Entry<String,ArrayList<String>> entry : wordMap.entrySet()) {
		  String word = entry.getKey();
		  ArrayList<String> sentenceIndexList = entry.getValue();
		  results.add(word + " {" + sentenceIndexList.size() +":"  + StringUtils.join(sentenceIndexList, ",") + "}");
		}
		return results;
	}
}
