package com.dropclip.concordance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SentenceParserTest {

	@Test
	public void getSentencesShouldReturnEmptyListWithNullParagraph() {
		String paragraph = null;
		SentenceParser sp = new SentenceParser(paragraph);
		
		assertEquals(0, sp.getSentences().size());
	}
	
	@Test
	public void getSentencesShouldReturnEmptyListWithEmptyStringParagraph() {
		String paragraph = "";
		SentenceParser sp = new SentenceParser(paragraph);
		
		assertEquals(0, sp.getSentences().size());
	}
	
	@Test
	public void getSentencesShouldReturnEmptyListWithWhiteSpaceParagraph() {
		String paragraph = "   ";
		SentenceParser sp = new SentenceParser(paragraph);
		
		assertEquals(0, sp.getSentences().size());
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatIncludesAnAcronym() {
		String paragraph = "This sentence has the U.S.A acronym in it.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence has the U.S.A acronym in it.", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatIncludesADecimal() {
		String paragraph = "This sentence has 10.0 decimal in it.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence has 10.0 decimal in it.", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatIncludesADate() {
		String paragraph = "This sentence has the 2016.01.19 date in it.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence has the 2016.01.19 date in it.", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatIncludesAPhoneNumber() {
		String paragraph = "This sentence has the 555.555.5555 phone number in it.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence has the 555.555.5555 phone number in it.", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatEndsWithAPeriod() {
		String paragraph = "This sentence ends with a period.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence ends with a period.", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatEndsWithAQuestionMark() {
		String paragraph = "This sentence ends with a question mark, really?";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence ends with a question mark, really?", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnOneSentenceThatEndsWithAnExclamationPoint() {
		String paragraph = "This sentence is awesome!";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		
		assertEquals(1, sentences.size());
		assertEquals("This sentence is awesome!", sentences.get(0));
	}
	
	@Test
	public void getSentencesShouldReturnFourSentences() {
		String paragraph = "A \"concordance\" is an alphabetical list of the words present in a text with a count of how "
			+"often each word appears and citations of where each word appears in the text (e.g., page "
			+"number). Write a program -- in the programming language of your choice -- that will "
			+"generate a concordance of an arbitrary text document written in English: the text can be "
			+"read from stdin, and the program should output the concordance to stdout or a file. For "
			+"each word, it should print the count and the sorted list of citations, in this case the "
			+"zero-indexed sentence number in which that word occurs. You may assume that the input "
			+"contains only spaces, newlines, standard English letters, and standard English punctuation "
			+"marks.";
		SentenceParser sp = new SentenceParser(paragraph);
		List<String> sentences = sp.getSentences();
		assertEquals(4, sentences.size());
		assertEquals("A \"concordance\" is an alphabetical list of the words present in a text with a count of how "
			+"often each word appears and citations of where each word appears in the text (e.g., page number).", sentences.get(0));
		assertEquals("Write a program -- in the programming language of your choice -- that will generate a concordance of an arbitrary text document written in English: the text can be "
			+"read from stdin, and the program should output the concordance to stdout or a file.", sentences.get(1));
		assertEquals("For each word, it should print the count and the sorted list of citations, in this case the zero-indexed sentence number in which that word occurs.", sentences.get(2));
		assertEquals("You may assume that the input contains only spaces, newlines, standard English letters, and standard English punctuation marks.", sentences.get(3));
	}

	

}
