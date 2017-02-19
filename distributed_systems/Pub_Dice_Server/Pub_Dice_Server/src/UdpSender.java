import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class UdpSender extends SenderThread {
	private DatagramSocket server;
	private BlockingQueue<Integer> port = new ArrayBlockingQueue<Integer>(1);
	private BlockingQueue<InetAddress> IPAddress = new ArrayBlockingQueue<InetAddress>(1);
	private BlockingQueue<byte[]> sendData = new ArrayBlockingQueue<byte[]>(1);

	public UdpSender(DatagramSocket server) {
		this.server = server;
	}

	@Override
	public void sendMessage(String message, int port, InetAddress IPAddress) {
		try {
			this.port.put(port);
			this.IPAddress.put(IPAddress);
			sendData.put(message.getBytes());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true)
		{
			try{
				byte[] sendData = this.sendData.take();
				if(sendData != null)
				{
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress.take(), port.take());
					try {
						server.send(sendPacket);
					} catch (IOException e) {
						System.out.println("NETWORK ERROR: Error trying to send packet");
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendMessage(String message) {
		
	}
}
