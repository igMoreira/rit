import java.math.BigInteger;

/**
 * Represents a group of the input file.
 * A group contains.
 * 
 * 	1. A 2048-bit RSA public modulus.
 *	2. A RSA public exponent.
 *	3. A RSA ciphertext.
 *
 * @author igMoreira
 *
 */
public class Group {

	private BigInteger modulus;
	private BigInteger publicExpoent;
	private BigInteger cipherText;
	
	/**
	 * CONSTRUCTOR
	 */
	public Group() {
	}

	/**
	 * Getter of the modulus.
	 * @return: BigInteger modulus
	 */
	public BigInteger getModulus() {
		return modulus;
	}

	/**
	 * Setter of the modulus
	 * @param modulus: BigInteger modulus
	 */
	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}

	/**
	 * Getter of the public expoent.
	 * @return: BigInteger public expoent
	 */
	public BigInteger getPublicExpoent() {
		return publicExpoent;
	}

	/**
	 * Setter of the public expoent
	 * @param publicExpoent: BigInteger public expoent
	 */
	public void setPublicExpoent(BigInteger publicExpoent) {
		this.publicExpoent = publicExpoent;
	}

	/**
	 * Getter of the cipher text
	 * @return: BigInteger ciphertext
	 */
	public BigInteger getCipherText() {
		return cipherText;
	}

	/**
	 * Setter of the cipher text
	 * @param cipherText: Big Integer cipher text
	 */
	public void setCipherText(BigInteger cipherText) {
		this.cipherText = cipherText;
	}
}
