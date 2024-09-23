import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Chloe He	
 *	@since	September 23, 2024
 */
 
 // diff (cat Macbeth.txt) (cat notcrypted.txt)
 // add file utils
 //		System.out.println("\nThe encrypted file " + outputFile + "has been created using the keyword -> " + key.toUpperCase());
 
 
 
public class MVCipher {
	
	private	String	key;	// key used for encoding/decoding
	private	String	inputLine = "";
	private	final	int	MIN_KEY_LEN; // minimum key length needed for input
	private	final	int	ENCRYPT_CHOICE; // number input for selecting encrypt
	private	String	inputFile;
	private String	outputFile;
	private	int		choice;
	
	
		
	/** Constructor */
	public MVCipher() { 
		key = "";
		MIN_KEY_LEN = 3;
		ENCRYPT_CHOICE = 1;
	}
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		
		// getting key's input
		String key = "";
		while (key.length() < MIN_KEY_LEN) {
			key = Prompt.getString("Please input a word to use as key (letters only)");
			if (key.length() < MIN_KEY_LEN) { // see if all letters
				System.out.println("ERROR: Key must be all letters and at least 3 characters long");
			}
		} 
		System.out.println();
		
		choice = Prompt.getInt("Encrypt or decrypt? (1, 2)");
				
		readAndWrite();
		
		System.out.println("\nThe encrypted file " + outputFile + "has been created using the keyword -> " + key.toUpperCase());
	}
	
	public void readAndWrite() {
		if (choice == ENCRYPT_CHOICE) inputFile = Prompt.getString("Name of file to encrypt");
		else inputFile = Prompt.getString("Name of file to decrypt");
		outputFile = Prompt.getString("Name of output file");


		Scanner input = FileUtils.openToRead(inputFile);
		PrintWriter output = FileUtils.openToWrite(outputFile);
		
		while (input.hasNextLine()) {
			inputLine = input.nextLine();
			output.println(cryptString(inputLine));
		}
		
		output.close();
	}
	
	public String cryptString(String line) {
		String returnline = "";
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			int ind = (int)(ch);
			if (ind >= 97 && ind <= 122) returnline += (char)(cryptLowerChar(ind, i));
			else if (ind >= 65 && ind <= 90) returnline += (char)(cryptUpperChar(ind, i)); 
			else returnline += " ";
		}
		return returnline;
	}
	
	public int cryptLowerChar(int ind, int keyind) {
		keyind = (int)((key.charAt(keyind % key.length())).toLowerCase()) - 65; // change to const
		int newind = ind + keyind;
		if (newind > 122) newind -= 26;
		return (newind);
	}
	
	public int cryptUpperChar(int ind, int keyind) {
		keyind = (int)(key.charAt(keyind % key.length()));
		int newind = ind + keyind;
		if (newind > 90) newind -= 26;
		return (newind);
	}
}

// do decrypt
