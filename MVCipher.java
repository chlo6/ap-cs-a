import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - encrypts and decrypts a txt file using a variation of the 
 * 	Caesar cipher, which takes a keyword, turns it to uppercase, then 
 * 	uses it as mutliple Caesar ciphers. Preserves case (upper/lower), 
 * 	characters,spaces, etc, when being encrypted/decrypted.
 * 
 *	Requires Prompt and FileUtils classes.
 * 	To run - put in folder with Prompt.java and FileUtils.java
 *	
 *	@author	Chloe He	
 *	@since	September 23, 2024
 */
 
 // diff (cat Macbeth.txt) (cat notcrypted.txt)
 
 
public class MVCipher {
	
	private	String	key;	// key used for encoding/decoding
	private	String	inputLine = "";
	private	final	int	MIN_KEY_LEN; // minimum key length needed for input
	private	final	int	ENCRYPT_CHOICE; // number input for selecting encrypt
	private	String	inputFile;
	private String	outputFile;
	private	int		choice;
	private int 	ind;
	
		
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
		System.out.println("\nWelcome to the MV Cipher machine!\n");
		
		
		// getting key's input
		boolean foundKey = false;
		while (!foundKey) {
			key = Prompt.getString("Please input a word to use as key"
								 + "(letters only)");
			foundKey = true;
			for (int i = 0; i < key.length(); i++) {
				key = key.toUpperCase();
				if(key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
					foundKey = false;
				}
			}
			if (key.length() < MIN_KEY_LEN) foundKey = false;
			if (!foundKey) { 
				System.out.println("ERROR: Key must be all letters and"
								 + " at least 3 characters long");
			}
		} 
		
		
		ind = key.length()-1;

		System.out.println();
		
		choice = Prompt.getInt("Encrypt or decrypt? (1, 2)");
				
		readAndWrite();
		
		System.out.println("\nThe encrypted file " + outputFile + " has"
						 + " been created using the keyword -> " + key);
						 

	}
	
	public void readAndWrite() {


		if (choice == ENCRYPT_CHOICE) {
			inputFile = Prompt.getString("Name of file to encrypt");
		} else inputFile = Prompt.getString("Name of file to decrypt");
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
			System.out.println((int)key.charAt(ind%key.length()));
			int cryptAmt = (int)key.charAt(ind%key.length()) - 64; 
			
			char newch = ch;
			
			if (ch != 32) {
				if (choice == 1) {
					newch += cryptAmt;
					if (ch >= 'a' && ch <= 'z' && newch > 'z') newch -= 26;
					if (ch >= 'A' && ch <= 'Z' && newch > 'Z') newch -= 26;
				} else {
					newch -= cryptAmt;
					if (ch >= 'a' && ch <= 'z' && newch < 'a') newch += 26;
					if (ch >= 'A' && ch <= 'Z' && newch < 'A') newch += 26;
				}
				ind++;
			} else newch = ' ';
			
			//System.out.println(ch + " " + (int)(ch) + "\t" + (key.charAt(ind%key.length())) + " "+ cryptAmt + "\t" + newch + " " + (int)(newch));
		
			
			returnline += newch;
		}
			
			
		return returnline;
	}
}
