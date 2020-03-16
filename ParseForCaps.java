import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Prompt for a file to read. Read the file one line at a time, break each line into individual
 * words, and output the first letter for each word if it is capitalized.
 * 
 * @author Matt Fuller
 */
public class ParseForCaps {

	public static final int ERROR_CODE = 1;

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		// Creates a keyboard Scanner
		Scanner kbd = new Scanner(System.in);

		System.out.print("Please enter a plain text file name: ");
		String filename = kbd.nextLine().trim();
		kbd.close();

		System.out.println(filename);
		System.out.println();

		// Creates a File object
		File file = new File("lab09/" + filename);
		System.out.println(file);

		try {
			// Reads each word from the line
			Scanner fileScan = new Scanner(file);

			System.out.print("Capital first letters: ");

			// Reads in each line of the file with fileScan until we run out of lines
			while (fileScan.hasNextLine()) {
				// read one line
				String line = fileScan.nextLine();

				Scanner lineScan = new Scanner(line);

				// Reads each word from the line until we run out of words
				while (lineScan.hasNext()) {
					// reads the next word
					String word = lineScan.next();

					// Prints the first letter of each word if it is capitalized
					char c1 = word.charAt(0);
					boolean b1 = Character.isUpperCase(c1);
					if (b1 == true) {
						System.out.print(c1);
					}
				}

				lineScan.close();
			}
			fileScan.close();
		}
		// Prints a message before exiting if the file does not exist
		catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" could not be opened.");
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		}

	}
}
