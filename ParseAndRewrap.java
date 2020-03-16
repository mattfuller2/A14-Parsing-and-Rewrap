
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads in a file and wraps the words at a given line length.
 * 
 * @author Matt Fuller
 * @author Lawrence Kimsey
 */
public class ParseAndRewrap {

	public static final int ERROR_CODE = 1;
	private static String filename; // name of plain text file
	private static int lineMax; // maximum number of characters per line
	private static int lineTotal = 0, shortestLine = 999, longestLine = 0;

	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);

		System.out.print("Please enter a plain text file name: ");
		filename = kbd.nextLine().trim();

		System.out.print("Please enter the maximum number of characters in a single line: ");
		lineMax = kbd.nextInt();
		if (lineMax > 0 && lineMax < 100) {
			kbd.close();
		}
		System.out.println();

		System.out.println(filename + " reformatted with maximum line length of " + lineMax + ":");

		System.out.println();

		File file = new File("/lab09-Word Wrapping/" + filename);

		try {
			Scanner fileScan = new Scanner(file);

			// Reads in each line of the file with fileScan until we run out of
			// lines
			while (fileScan.hasNextLine()) {
				// read one line
				fileScan.nextLine();

				// Reads each word from the line until we run out of words
				while (fileScan.hasNext()) {
					// reads and prints next word on one line until maxLine is
					// reached
					for (lineTotal = 0; lineTotal <= lineMax;) {
						try {
							String word = fileScan.next() + " ";
							if (lineTotal + word.length() <= lineMax) {
								lineTotal = lineTotal + (word.length());
								System.out.print(word);
							} else if (lineTotal + word.length() == lineMax + 1) {
								word = word.substring(0, word.length() - 1);
								lineTotal = lineTotal + word.length() - 1;
								System.out.print(word);

								lineTotal = lineMax;
								longestLine = lineTotal;

							} else {
								System.out.println();
								System.out.print(word);
								if (lineTotal < shortestLine) {
									shortestLine = lineTotal;
								}
								lineTotal = word.length();

							}

						}
						catch (NoSuchElementException e1) {
							if (lineTotal < shortestLine) {
								shortestLine = lineTotal;
							}
							break;
						}

					}
				}

				// Prints the longest and shortest line in the reformatted
				// output
				System.out.println();
				System.out.println();
				System.out.println("Longest line: " + longestLine);
				System.out.println("Shortest line: " + shortestLine);

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
