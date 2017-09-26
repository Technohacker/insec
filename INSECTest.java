import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>INSEC Algorithm Test Case</h1>
 * <p>This class is used to test the INSEC cipher</p>
 *
 */
public class INSECTest {
	/**
	 * Main method to utilize the cipher.
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {

		// Create a BufferedReader to receive input from the user.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Print out information about me and the algorithm.
		System.out.println("Welcome to the INSEC algorithm.");
		System.out.println("==============================");
		System.out.println("NOTE: When copying the encryption output, copy everything from the first letter to the end of the line.");
		System.out.println("NOTE 2: Some inputs might generate exceptions due to large message lengths");
		System.out.println();

		// boolean to check whether we need to continue running the loop or
		// stop.
		boolean shouldContinue = true;

		// Loop to receive user input.
		while (shouldContinue) {
			// Ask whether we have to encrypt or decrypt.
			System.out.print("Encrypt (1) or Decrypt (2)?: ");
			int choice = Integer.parseInt(in.readLine());

			// Check what is their choice.
			switch (choice) {
			case 1:
				// We have to encrypt.

				// Get message.
				System.out.print("Enter Message To Encrypt: ");
				String message = in.readLine();

				// Get encryption key.
				System.out.print("Enter Encryption Key: ");
				String key = in.readLine();
				System.out.println();

				// Encrypt the message.
				String cipher = INSEC.encrypt(message, key);

				// And show it to the user.
				System.out.println("Encrypted message: " + cipher);
				break;
			case 2:
				// We have to decrypt.

				// Get cipher text.
				System.out.print("Enter Encrypted Message: ");
				String encMessage = in.readLine();

				// Get key used to encrypt it.
				System.out.print("Enter Encryption Key: ");
				String decKey = in.readLine();
				System.out.println();

				// Decrypt the message.
				String decMessage = INSEC.decrypt(encMessage, decKey);

				// And show it to the user.
				System.out.println("Decrypted message: " + decMessage);
				break;
			default:
				// Guess the user didn't understand.
				System.out.println("Choose 1 or 2");
				continue;
			}

			// Should we continue, User?
			System.out.print("Continue? (Y/N): ");
			String cont = in.readLine();

			if (cont.equalsIgnoreCase("Y")) {
				// We have to continue.
				System.out.println();
				shouldContinue = true;
			} else if (cont.equalsIgnoreCase("N")) {
				// Goodbye, User!
				System.out.println("Goodbye! :)");
				shouldContinue = false;
			} else {
				// Let's just go now.
				System.out.println("(Assuming N...) Goodbye! :)");
				shouldContinue = false;
			}
		}

		// Close the BufferedReader to prevent memory leaks
		in.close();
	}
}
