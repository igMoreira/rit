import java.util.List;

/**
 * Provides a interface to different Rsa attacks.
 * 
 * @author igMoreira
 *
 */
public interface RsaAttack {
	
	/**
	 * Execute an attack giving some
	 * group data.
	 * 
	 * @param groups: The list of groups found in the file.
	 */
	public void execute(List<Group> groups);
}
