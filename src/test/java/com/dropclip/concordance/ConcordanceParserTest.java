package com.dropclip.concordance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ConcordanceParserTest {

	@Test
	public void getAlphabeticalListOfResultsShouldReturnEmptyListWithNullParagraph() {
		String paragraph = null;
		ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		assertEquals(0, cp.getAlphabeticalListOfResults().size());
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnEmptyListWithEmptyStringParagraph() {
		String paragraph = "";
		ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		assertEquals(0, cp.getAlphabeticalListOfResults().size());
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnEmptyListWithWhiteSpaceParagraph() {
		String paragraph = "   ";
		ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		assertEquals(0, cp.getAlphabeticalListOfResults().size());
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnThreeWordsAllLowercase() {
		String paragraph = "This is short.";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(3, results.size());
		assertEquals("is {1:0}", results.get(0));
		assertEquals("short {1:0}", results.get(1));
		assertEquals("this {1:0}", results.get(2));
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnThreeWordsWithOneHavingADash() {
		String paragraph = "This is zero-indexed.";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(3, results.size());
		assertEquals("is {1:0}", results.get(0));
		assertEquals("this {1:0}", results.get(1));
		assertEquals("zero-indexed {1:0}", results.get(2));
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnFourWordsWithOneHavingThreePeriods() {
		String paragraph = "This is U.S.A. today.";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(4, results.size());
		assertEquals("is {1:0}", results.get(0));
		assertEquals("this {1:0}", results.get(1));
		assertEquals("today {1:0}", results.get(2));
		assertEquals("u.s.a. {1:0}", results.get(3));
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnThreeWordsWithoutTheLastPeriodInUSA() {
		String paragraph = "This is U.S.A";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(3, results.size());
		assertEquals("is {1:0}", results.get(0));
		assertEquals("this {1:0}", results.get(1));
		assertEquals("u.s.a {1:0}", results.get(2));
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnThreeWordsWithoutTheLastPeriodInEG() {
		String paragraph = "This is e.g.,";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(3, results.size());
		assertEquals("e.g {1:0}", results.get(0));
		assertEquals("is {1:0}", results.get(1));
		assertEquals("this {1:0}", results.get(2));
		
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnTenWordsWithOneBeingEG() {
		String paragraph = "BreakIterator is better and less complicated than regex (e.g., patterns).";
	    ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(10, results.size());
		assertEquals("and {1:0}", results.get(0));
		assertEquals("better {1:0}", results.get(1));
		assertEquals("breakiterator {1:0}", results.get(2));
		assertEquals("complicated {1:0}", results.get(3));
		assertEquals("e.g. {1:0}", results.get(4));
		assertEquals("is {1:0}", results.get(5));
		assertEquals("less {1:0}", results.get(6));
		assertEquals("patterns {1:0}", results.get(7));
		assertEquals("regex {1:0}", results.get(8));
		assertEquals("than {1:0}", results.get(9));
	}
	
	@Test
	public void getAlphabeticalListOfResultsShouldReturnSeventyOneWords() {
		String paragraph = "A \"concordance\" is an alphabetical list of the words present in a text with a count of how "
			+"often each word appears and citations of where each word appears in the text (e.g., page "
			+"number). Write a program -- in the programming language of your choice -- that will "
			+"generate a concordance of an arbitrary text document written in English: the text can be "
			+"read from stdin, and the program should output the concordance to stdout or a file. For "
			+"each word, it should print the count and the sorted list of citations, in this case the "
			+"zero-indexed sentence number in which that word occurs. You may assume that the input "
			+"contains only spaces, newlines, standard English letters, and standard English punctuation "
			+"marks.";
        ConcordanceParser cp = new ConcordanceParser(paragraph);
		
		List<String> results = cp.getAlphabeticalListOfResults();
		assertEquals(71, results.size());
		assertEquals("a {6:0,0,0,1,1,1}", results.get(0));
		assertEquals("alphabetical {1:0}", results.get(1));
		assertEquals("an {2:0,1}", results.get(2));
		assertEquals("and {4:0,1,2,3}", results.get(3));
		assertEquals("appears {2:0,0}", results.get(4));
		assertEquals("arbitrary {1:1}", results.get(5));
		assertEquals("assume {1:3}", results.get(6));
		assertEquals("be {1:1}", results.get(7));
		assertEquals("can {1:1}", results.get(8));
		assertEquals("case {1:2}", results.get(9));
		assertEquals("choice {1:1}", results.get(10));
		assertEquals("citations {2:0,2}", results.get(11));
		assertEquals("concordance {3:0,1,1}", results.get(12));
		assertEquals("contains {1:3}", results.get(13));
		assertEquals("count {2:0,2}", results.get(14));
		assertEquals("document {1:1}", results.get(15));
		assertEquals("e.g. {1:0}", results.get(16));
		assertEquals("each {3:0,0,2}", results.get(17));
		assertEquals("english {3:1,3,3}", results.get(18));
		assertEquals("file {1:1}", results.get(19));
		assertEquals("for {1:2}", results.get(20));
		assertEquals("from {1:1}", results.get(21));
		assertEquals("generate {1:1}", results.get(22));
		assertEquals("how {1:0}", results.get(23));
		assertEquals("in {6:0,0,1,1,2,2}", results.get(24));
		assertEquals("input {1:3}", results.get(25));
		assertEquals("is {1:0}", results.get(26));
		assertEquals("it {1:2}", results.get(27));
		assertEquals("language {1:1}", results.get(28));
		assertEquals("letters {1:3}", results.get(29));
		assertEquals("list {2:0,2}", results.get(30));
		assertEquals("marks {1:3}", results.get(31));
		assertEquals("may {1:3}", results.get(32));
		assertEquals("newlines {1:3}", results.get(33));     
		assertEquals("number {2:0,2}", results.get(34));
		assertEquals("occurs {1:2}", results.get(35));
		assertEquals("of {6:0,0,0,1,1,2}", results.get(36));
		assertEquals("often {1:0}", results.get(37));
		assertEquals("only {1:3}", results.get(38));
		assertEquals("or {1:1}", results.get(39));
		assertEquals("output {1:1}", results.get(40));
		assertEquals("page {1:0}", results.get(41));
		assertEquals("present {1:0}", results.get(42));
		assertEquals("print {1:2}", results.get(43));
		assertEquals("program {2:1,1}", results.get(44));
		assertEquals("programming {1:1}", results.get(45));
		assertEquals("punctuation {1:3}", results.get(46));
		assertEquals("read {1:1}", results.get(47));
		assertEquals("sentence {1:2}", results.get(48));
		assertEquals("should {2:1,2}", results.get(49));
		assertEquals("sorted {1:2}", results.get(50));
		assertEquals("spaces {1:3}", results.get(51));
		assertEquals("standard {2:3,3}", results.get(52));
		assertEquals("stdin {1:1}", results.get(53));
		assertEquals("stdout {1:1}", results.get(54));
		assertEquals("text {4:0,0,1,1}", results.get(55));
		assertEquals("that {3:1,2,3}", results.get(56));
		assertEquals("the {10:0,0,1,1,1,1,2,2,2,3}", results.get(57));
		assertEquals("this {1:2}", results.get(58));
		assertEquals("to {1:1}", results.get(59));
		assertEquals("where {1:0}", results.get(60));
		assertEquals("which {1:2}", results.get(61));
		assertEquals("will {1:1}", results.get(62));
		assertEquals("with {1:0}", results.get(63));  
		assertEquals("word {4:0,0,2,2}", results.get(64));
		assertEquals("words {1:0}", results.get(65));
		assertEquals("write {1:1}", results.get(66));
		assertEquals("written {1:1}", results.get(67));
		assertEquals("you {1:3}", results.get(68));
		assertEquals("your {1:1}", results.get(69));
		assertEquals("zero-indexed {1:2}", results.get(70));
	}

}
