/***
 * Defines an interface for block cipher algorithms
 * @author igMoreira
 *
 */
public interface BlockCipher {
	/***
	 * Defines the execution method for a block cipher algorithm
	 * @param state: The 64-bit input state provided by the user 
	 * @return: The 64-bit key stream
	 */
	public byte[] execute(byte[] state);
}
