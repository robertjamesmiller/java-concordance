package com.dropclip.concordance;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class ConcordanceParser {
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
			System.out.println(sentence);
			// Parse a sentence into words
			// @TODO: move word parsing into its own object with its own set of tests
			BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
			wordIterator.setText(sentence);
		    int start = wordIterator.first();
		    int end = wordIterator.next();
		    while (end != BreakIterator.DONE) {
		    	// lowercase all words
		        String word = sentence.substring(start,end).toLowerCase();
		        // BreakIterator is not working for e.g., and U.S.A. so check if word
		        // has a period and, if so, check to see
		        if (word.equals("e.g") && sentence.substring(start,end+2).equals("e.g.,")){
		        	word = "e.g.";
		        } else if (word.equals("u.s.a") && sentence.substring(start,end+1).toLowerCase().equals("u.s.a.")){
		        	word = "u.s.a.";
		        } 
		        if (Character.isLetterOrDigit(word.charAt(0))) {
		        	if (wordMap.containsKey(word)){
						wordMap.get(word).add(""+sentenceIndex);
					} else {
						ArrayList<String> sentenceIndexList = new ArrayList<String>();
						sentenceIndexList.add(""+sentenceIndex);
						wordMap.put(word, sentenceIndexList);
					}
		        }
		        start = end;
		        end = wordIterator.next();
		    }
			sentenceIndex++;
		}
		// @TODO: Move formatting into a separate object
		for(Map.Entry<String,ArrayList<String>> entry : wordMap.entrySet()) {
		  String word = entry.getKey();
		  ArrayList<String> sentenceIndexList = entry.getValue();
		  results.add(word + " {" + sentenceIndexList.size() +":"  + StringUtils.join(sentenceIndexList, ",") + "}");
		}
		return results;
	}
}
