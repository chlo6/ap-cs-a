import java.util.ArrayList;

/**
 *	AnagramMaker - generates all anagram phrases, in alphabetical
 * 	order, based off an input of an initial string, the number of words 
 * 	desired, and the number of phrases desired. Created anagrams will
 * 	use up all the letters given in the original string
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Chloe He
 *	@since	January 15th, 2025
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	private SortMethods sm;
	private String string;
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
		
		sm = new SortMethods();
	}
	
	/** 
	 * 	Main - runs AnagramMaker
	 */
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	
	 * 	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then call method that
	 * 	creates anagrams from those characters.
	 */
	public void runAnagramMaker() {
		boolean quit = false;
		
		while (!quit) {
			numPhrases = 0;
			// get inputs
			String oldStr = Prompt.getString("\nWord(s), name or phrase (q to quit)");
			
			if (oldStr.equals("q")) quit = true;
			else {
				numWords = Prompt.getInt("Number of words in anagram"); 
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
				System.out.println();
				
				// take out non-alphabet
				string = "";
				for (int i = 0; i < oldStr.length(); i++) {
					if (Character.isLetter(oldStr.charAt(i))) string += ("" + oldStr.charAt(i)).toLowerCase();
				}
				
				makeAnagram(string, new ArrayList<>());
				
				System.out.println("\nStopped at " + maxPhrases + " anagrams");
			}
		}
	}
	
	/**
	 * 	@param	str - current unused letters
	 * 	@param	words - current list of words in the current phrase
	 * 	Makes the anagram by generating all possible ones and 
	 * 	filtering through them using the base case
	 */
	public void makeAnagram(String str, ArrayList<String> words) {	
		// base cases 
		if (numPhrases >= maxPhrases) return; // printed enough phrases
		// used up all letters // too many words
		if (str.length() == 0 || words.size() >= numWords) { 
			if (str.length() == 0 && words.size() == numWords) {
				for (int i = 0; i < words.size(); i++) {
					System.out.print(words.get(i) + " ");
				} System.out.println();
				numPhrases++;
			}
			return;
		}
		
		// generate possible words
		ArrayList<String> possibleWords = wu.allWords(str);
		sm.mergeSort(possibleWords);
		
		// look thru generated words, make recursive calls + backtrack
		for (int j = 0; j < possibleWords.size(); j++) {
			String newWord = possibleWords.get(j);
			
			// remove the letters
			int[] newFreq = new int [26];
			for (int i = 0; i < 26; i++) newFreq[i] = 0;
			for (int i = 0; i < newWord.length(); i++) {
				char ch = newWord.charAt(i);
				newFreq[ch-'a']++;
			}
			String newString = "";
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (newFreq[ch-'a'] <= 0) newString += "" + ch;
				else newFreq[ch-'a']--;
			}
			
			words.add(newWord);
			makeAnagram(newString, words);
			words.remove(words.size()-1);
		}
	}
}
