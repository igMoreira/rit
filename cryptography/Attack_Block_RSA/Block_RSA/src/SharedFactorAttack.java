import java.math.BigInteger;
import java.util.List;

/**
 * Implements a shared factor attack in a RSA
 * cipher.
 * 
 * @author igMoreira
 *
 */
public class SharedFactorAttack implements RsaAttack{

	/**
	 * Execute an attack giving some
	 * group data.
	 * 
	 * @param groups: The list of groups found in the file.
	 */
	@Override
	public void execute(List<Group> groups) {
		for (int i = 0; i < groups.size(); i++) {
			BigInteger decryptionKey = null;
			for(int j = 0; j < groups.size(); j++)
			{
				if(j == i)
					continue;
				decryptionKey = this.computePrivateKey(groups.get(i).getModulus(), groups.get(i).getPublicExpoent(), groups.get(j).getModulus());
				if(decryptionKey != null)
					break;
			}
			BlockRSA block = new BlockRSA(groups.get(i).getModulus(), groups.get(i).getPublicExpoent(),decryptionKey);
			String pt = block.decrypt(groups.get(i).getCipherText());
			System.out.println(pt);
		}
	}
	
	/**
	 * Compute the private key of a group given a modulus A,
	 * a modulus B, and the encryption exponent A.
	 * 
	 * @param modulusAlex: The modulus of the group you wish to compute the private key.
	 * @param encryptionExponentAlex: The public exponent of the group you wish to compute the private key.
	 * @param modulusBlake: The modulus of a group that holds a common prime factor. 
	 * @return: The private key of Alex.
	 */
	private BigInteger computePrivateKey(BigInteger modulusAlex, BigInteger encryptionExponentAlex, BigInteger modulusBlake)
	{
		BigInteger p = modulusAlex.gcd(modulusBlake);
		if(p.equals(BigInteger.ONE))
			return null;
		BigInteger q = modulusAlex.divide(p);
		return encryptionExponentAlex.modInverse(PublicKeyMath.eulerTotient(p, q));
	}

	
}