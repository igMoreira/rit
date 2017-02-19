import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a java application to attack a
 * given Rsa implementation as a "block"
 * cipher.
 * 
 * @author igMoreira
 *
 */
public class RsaDecrypt {

	/**
	 * MAIN
	 * @param args: User input data
	 */
	public static void main(String[] args) {
		usage(args);
		BufferedReader in = null;
		try {
			FileReader file = new FileReader(args[0]);
			in = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("FILE ERROR: The file does not exist or could not be opened");
			System.exit(0);
		}
		List<Group> groups = new ArrayList<Group>();
		String line;
		Group group = null;
		try {
			for(int i=0; (line = in.readLine()) != null ;i++)
			{
				switch(i%3)
				{
				case 0:
					group = new Group();
					group.setModulus(new BigInteger(line));
					break;
				case 1:
					group.setPublicExpoent(new BigInteger(line));
					break;
				case 2:
					group.setCipherText(new BigInteger(line));
					groups.add(group);
				}
			}
		} catch (IOException e) {
			System.out.println("FILE ERROR: Error trying to read the file.");
			System.exit(0);
		}
		RsaAttack attack = new SharedFactorAttack();
		attack.execute(groups);
	}
	
	/**
	 * Verifies the syntax of the application call
	 * @param args: The user input
	 */
	private static void usage(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("SYNTAX ERROR: java RsaDecrypt <file>");
			System.out.println("<file> is the input file name");
			System.out.println("The input file must consist of two or more groups");
			System.out.println("Each group must consist of three lines, containing an RSA public modulus, an RSA public exponent, and an RSA ciphertext, respectively.");
			System.out.println("Each line must consist of one decimal BigInteger.");
			System.exit(0);
		}
	}
	
}
