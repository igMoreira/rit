import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;


public class UdpReceiver extends ReceiverThread {
	private DatagramSocket server;
	private byte[] receiveData;
	private DatagramPacket receivePacket;
	
	public UdpReceiver(DatagramSocket server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		while(true)
		{
			receiveData = new byte[100];
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				server.receive(receivePacket);
			} catch (IOException e) {
				System.out.println("NETWORK ERROR: Error receiving packet");
				e.printStackTrace();
				System.exit(0);
			}
			int count;
			for(count =0;count < receiveData.length; count++ )
			{
				if(receiveData[count] == 0)
					break;
			}
			receiveData = Arrays.copyOfRange(receiveData, 0, count);
			ResponseController handler = new ResponseController(
					new String(receiveData),
					receivePacket.getPort(),
					receivePacket.getAddress());
			handler.start();
		}
	}
}
