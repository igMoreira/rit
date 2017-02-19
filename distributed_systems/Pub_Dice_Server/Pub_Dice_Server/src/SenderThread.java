import java.net.InetAddress;

/**
 * Provides an interface for the sender,
 * the sender thread can use different methods
 * to send message, i.e. Binary, text, object, etc.
 *  
 * @author igMoreira
 *
 */
public abstract class SenderThread extends Thread{
	
	public abstract void sendMessage(String message);
	public abstract void sendMessage(String message, int port, InetAddress IPAddress);
}
