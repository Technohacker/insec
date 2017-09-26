/**
 * <h1>INSEC: I'm Not a Secure Encryption Cipher</h1>
 * <p>
 * INSEC is an encryption algorithm made by me.
 * </p>
 * <b>DISCLAIMER: As the name implies, this algorthm is not secure and
 * therefore, should not be used for any confidential data.</b>
 * 
 * <p>
 * This class provides methods to encrypt and decrypt a string using INSEC. It
 * is heavily commented for understandability purposes.
 * </p>
 * 
 * @version 1.0.0
 *
 */
public class INSEC {

    /**
     * String right padding method (utility method for the INSEC cipher)
     * 
     * @param arg0
     *            String to be padded
     * @param padLength
     *            Amount of padding to be added
     * @return Padded String
     */
    private static String rightPad(String arg0, int padLength) {
        char[] chArr = arg0.toCharArray();
        char[] paddedString = new char[chArr.length + padLength];
        for (int i = 0; i < chArr.length; i++) {
            paddedString[i] = chArr[i];
        }

        for (int j = chArr.length; j < paddedString.length; j++) {
            paddedString[j] = ' ';
        }
        return new String(paddedString);
    }

    /**
     * <h1>INSEC Encryption Method</h1>
     * <p>
     * The whole encryption process can be summarized as:
     * 
     * <ol>
     *      <li>Pad out the message with whitespace so that the length of the message is divisible by the key's length</li>
     *      <li>Divide the (padded) message into blocks of <code>char<code>s where each block has a length equal to the length of the key</li>
     *      <li>Encrypt the <code>char</code>:</li>
     *      <ol>
     *          <li>Take one <code>char</code> from the current block of chars</li>
     *          <li>XOR with each <code>char</code> of the key upto the current message index</li>
     *          <li>Get the two's compliment of the result</li>
     *          <li>Cast to <code>char</code> (bitwise operations return integers)</li>
     *      </ol>
     *      <li>Convert cipher text <code>char</code> array to <code>String</code> and return</li>
     * </ol>
     * 
     * @param message
     *            A String containing the message
     * @param key
     *            A String containing the key
     * @return String containing the encrypted message
     */
    public static String encrypt(String message, String key) {
        // Calculate the amount of padding necessary to make (message length)
        // divisible by (key length)
        int padLength;
        if ((message.length() == key.length()) || (message.length() % key.length() == 0)) {
            // No padding needed
            padLength = 0;
        } else if (message.length() < key.length()) {
            // Key is greater than message. Pad out message to be longer than
            // key
            padLength = message.length() + key.length() + (key.length() % message.length());
        } else {
            // Padding necessary
            padLength = key.length() - (message.length() % key.length());
        }

        // Pad out the message array by the number calculated above.
        String paddedMsg = INSEC.rightPad(message, padLength);

        // Convert the key string to a char array
        char[] keyArr = key.toCharArray();

        // Create a char array containing the resulting cipher text
        char[] cipherArr = new char[paddedMsg.length()];

        // Break the padded message into "blocks" of (key length) chars each
        for (int offset = 0; offset < paddedMsg.length(); offset += keyArr.length) {
            // Get a block of chars from the message
            char[] currentBlock = paddedMsg.substring(offset, offset + keyArr.length).toCharArray();

            // Loop through the current block of chars
            for (int i = 0; i < currentBlock.length; i++) {
                // Set the cipher text char corresponding to the first char in
                // the current block
                cipherArr[offset + i] = currentBlock[i];

                // Now, loop through the key char array
                int j = 0;
                do {
                    // XOR the current cipher text key with each char of the key
                    // upto the current cipher text index, get its two's
                    // compliment and cast to char
                    cipherArr[offset + i] = (char) (~(cipherArr[offset + i] ^ keyArr[j]));
                    
                    // Increment loop index
                    j++;
                } while(j < i);
            }
        }

        return new String(cipherArr);
    }

    /**
     * <h1>INSEC Decryption Method</h1>
     * <p>
     * The whole decryption process can be summarized as:
     * </p>
     * <ol>
     * <li>Convert cipher text and key to <code>char</code> arrays.</li>
     * <li>Create a <code>char</code> array for the decrypted message of the same length
     * as the cipher text</li>
     * <li>Decrypt the <code>char</code>:</li>
     *      <ol>
     *          <li>Take one <code>char</code> from the current block of chars</li>
     *          <li>XOR with each <code>char</code> of the key upto the current message index</li>
     *          <li>Get the two's compliment of the result</li>
     *          <li>Cast to <code>char</code> (bitwise operations return integers)</li>
     *      </ol>
     * <li>Convert decrypted <code>char</code> array to String</li>
     * <li>Trim padding spaces and return</li>
     * </ol>
     * 
     * @param cipher
     *            String containing the cipher text
     * @param key
     *            String containing the key used to encrypt the message
     * @return String containing the decrypted message
     */
     public static String decrypt(String cipher, String key) {
        // First, convert the cipher text and key to character arrays
        char[] cipherArr = cipher.toCharArray();
        char[] keyArr = key.toCharArray();

        // Then, create a character array for the decrypted message
        char[] msgArr = new char[cipherArr.length];

        // Break the padded message into "blocks" of (key length) chars each
        for (int offset = 0; offset < cipher.length(); offset += keyArr.length) {
            // Get a block of chars from the message
            char[] currentBlock = cipher.substring(offset, offset + keyArr.length).toCharArray();

            // Loop through the current block of chars
            for (int i = 0; i < currentBlock.length; i++) {
                // Set the cipher text char corresponding to the first char in
                // the current block
                msgArr[offset + i] = currentBlock[i];

                // Now, loop through the key char array
                int j = 0;
                do {
                    msgArr[offset + i] = (char) (~(msgArr[offset + i] ^ keyArr[j]));
                    j++;
                } while(j < i);
            }
        }

        // Finally, create a String containing the decrypted message with our
        // padding spaces removed
        return new String(msgArr).trim();
    }
}
