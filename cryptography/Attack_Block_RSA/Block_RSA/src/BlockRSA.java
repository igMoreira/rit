import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Provides a RSA use as a "block"
 * cipher.
 * 
 * @author igMoreira
 *
 */
public class BlockRSA {
	private RSA cipher = null;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param modulus: A BigInteger RSA modulus
	 * @param encryptionExponent: A BigInteger public exponent
	 * @param decryptionExponent: A BigInteger private exponent
	 */
	public BlockRSA(BigInteger modulus, BigInteger encryptionExponent, BigInteger decryptionExponent) {
		this.cipher = new RSA();
		this.cipher.setEncryptionExponent(encryptionExponent);
		this.cipher.setModulus(modulus);
		this.cipher.setDecryptionExponent(decryptionExponent);
	}
	
	/**
	 * Uses the RSA as a "block" cipher to
	 * encrypt a given text.
	 * 
	 * @param plainText: A String text to encrypt
	 * @return: A string containing the cipher text. 2048-bit block.
	 */
	public BigInteger encrypt(String plainText)
	{
		byte[] sbytes = null;
		try {
			sbytes = plainText.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int N = sbytes.length;
		byte[] ptbytes = new byte[256];
		ptbytes[0] = 1;
		ptbytes[1] = (byte) N;
		System.arraycopy(sbytes, 0, ptbytes, 2, N+1);
		return null;
	}
	
	/**
	 * Uses the RSA as a "block" cipher to
	 * decrypt a given text.
	 * 
	 * @param cipherText: 2048-bit block
	 * @return: A string containing the plain text
	 */
	public String decrypt(BigInteger cipherText)
	{
		BigInteger pt = cipher.decrypt(cipherText);
		byte[] ptbytes = pt.toByteArray();
		int N = (ptbytes[1]) &0xFF;
		byte[] sbytes = new byte[N];
		for (int i = 2, j=0; i <= N+1; i++,j++) {
			sbytes[j] = ptbytes[i];
		}
		String s = null;
		try {
			s = new String (sbytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
