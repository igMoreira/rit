import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Udp extends CommunicationProtocol{

	public Udp(int port) {
		DatagramSocket server = null;
		try {
			server = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("SOCKET ERROR: Error trying to open socket port");
			e.printStackTrace();
			System.exit(0);
		}
		this.sender = new UdpSender(server);
		this.receiver = new UdpReceiver(server);
		receiver.setPriority(Thread.MAX_PRIORITY);
		this.sender.start();
		this.receiver.start();
	}
	
	@Override
	public void sendRequest(String request) {
		
	}

	@Override
	public void sendRequest(String request, int port, InetAddress IPAddress) {
		this.sender.sendMessage(request,port,IPAddress);
	}

}
