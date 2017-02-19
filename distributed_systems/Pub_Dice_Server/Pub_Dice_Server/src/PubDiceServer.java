

public class PubDiceServer {

	public static void main(String[] args) {
		usage(args);
		int port = 0;
		try{
			port = Integer.parseInt(args[1]);	
		}
		catch(Exception e)
		{
			System.out.println("PARAMETER ERROR: The port must be a valid number.");
			System.exit(0);
		}
		new ClientProxy(port);
		System.out.println("Server is running.");
	}
	
	private static void usage(String[] args)
	{
		if(args.length != 2)
		{
			System.out.println("SYNTAX ERROR: java PubDiceServer <host> <port>");
			System.out.println("<host> is the host name or IP address of the server.");
			System.out.println("<port> is the port number of the server");
			System.exit(0);
		}
	}
	
}
