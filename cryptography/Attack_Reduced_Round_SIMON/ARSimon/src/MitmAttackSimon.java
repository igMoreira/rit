import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.rit.util.Hex;
import edu.rit.util.Packing;


/**
 * Implements a known plaintext attack in SIMON 48/96
 * the attack used was the meet in the middle attack
 * divided in two parts, the bottom up and the top down
 * 
 * @author igMoreira
 *
 */
public class MitmAttackSimon {
	
	private SimonCipher oracle = new SimonCipher(); // SIMON 48/96 oracle
	private List<byte[]> subkeys = new ArrayList<byte[]>(); // used to store the three subkeys guessed as a result of the attack
	private byte[][] pt; // set of all plaintext provided by the user
	byte[][] ct; // set of all ciphertext provided by the user
	
	/**
	 * Constructor
	 * 
	 * @param pt: set of all plaintext provided by the user
	 * @param ct: set of all ciphertext provided by the user
	 */
	public MitmAttackSimon(byte[][] pt, byte[][] ct) {
		this.pt = pt;
		this.ct = ct;
	}
	
	/**
	 * Meet in the middle execution command
	 * @return: list of the subkeys for the three rounds
	 */
	public List<byte[]> execute()
	{
		searchSubkeys(pt[0], ct[0]);

		return subkeys;
	}

	/**
	 * Try to guess subkey 1, with that compute the subkey 2 and 3
	 * with the three keys it verifies if those are the correct ones
	 * by encrypting all the pt/ct pairs.
	 * 
	 * @param pt: cipher text to be encrypted
	 * @param ct: cipher text to be decrypted
	 */
	private void searchSubkeys(byte[] pt, byte[] ct)
	{
		byte[] aux = new byte[6];
		byte[] sub1 = new byte[3];
		byte[] result = new byte[pt.length];
		for(int guess = 0x00; guess <= 0xFFFFFF; guess++)
		{
			// use integer guess as the subkey 1
			byte[] unpacked = new byte[4];
			Packing.unpackIntBigEndian(guess, unpacked, 0);
			System.arraycopy(unpacked, 1, sub1, 0, 3);
			byte[] rotated = oracle.rotateText(Arrays.copyOfRange(pt, 0, 3));
			
			aux = oracle.encrypt(sub1, pt);
			
			// Compute sub 2 based on sub 1
			byte[] sub2 = new byte[3];
			
			rotated = oracle.rotateText(Arrays.copyOfRange(aux, 0, 3));
			
			rotated[0] = (byte) (rotated[0] ^ aux[3]);
			rotated[1] = (byte) (rotated[1] ^ aux[4]);
			rotated[2] = (byte) (rotated[2] ^ aux[5]);
			
			sub2[0] = (byte) (rotated[0] ^ ct[3]);
			sub2[1] = (byte) (rotated[1] ^ ct[4]);
			sub2[2] = (byte) (rotated[2] ^ ct[5]);
			
			aux = oracle.encrypt(sub2, aux);
			
			// Compute subkey 3 based on sub 2
			byte[] sub3 = new byte[3];
			
			rotated = oracle.rotateText(Arrays.copyOfRange(aux, 0, 3));
			
			rotated[0] = (byte) (rotated[0] ^ aux[3]);
			rotated[1] = (byte) (rotated[1] ^ aux[4]);
			rotated[2] = (byte) (rotated[2] ^ aux[5]);
			
			sub3[0] = (byte) (rotated[0] ^ ct[0]);
			sub3[1] = (byte) (rotated[1] ^ ct[1]);
			sub3[2] = (byte) (rotated[2] ^ ct[2]);
			
			// Verifies if subkeys work for every pt/ct pair
			boolean status = true;
			for(int i=0; i<this.pt.length && status; i++)
			{
				for (int j = 0; j < 3; j++) {
					switch(j)
					{
					case 0:
						result = oracle.encrypt(sub1, this.pt[i]);
						break;
					case 1:
						result = oracle.encrypt(sub2, result);
						break;
					case 2:
						result = oracle.encrypt(sub3, result);
						break;
					}
				}
				status = (Hex.toString(result).equalsIgnoreCase(Hex.toString(this.ct[i])));
			}
			
			// If it is true the keys are right
			if(status)
			{
				subkeys.add(sub1);
				subkeys.add(sub2);
				subkeys.add(sub3);
				break;
			}
		}
	}


}