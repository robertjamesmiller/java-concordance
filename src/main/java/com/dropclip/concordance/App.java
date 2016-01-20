package com.dropclip.concordance;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		// prompt for a paragraph
	    System.out.print("Enter a paragraph that ends with a ~: ");
	    Scanner scanner = new Scanner(System.in).useDelimiter("~");
	    while (scanner.hasNext()) {
			ConcordanceParser cp = new ConcordanceParser(scanner.next());
			for (String wordResult : cp.getAlphabeticalListOfResults()) {
				System.out.println(wordResult);
			}
			System.out.print("Enter another paragraph that ends with a ~ (or Control-D to quit): ");
	    }
	    scanner.close();
	}
	
}
