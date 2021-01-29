
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;





public class Server 
{
	public final int server_port = 4446;
	private final static boolean listening = true;
	private ServerSocket server_socket;
	private Socket client_socket; 
	private String just_connected;// name of the client that just connected 





	public Server() throws IOException //constructor 
	{

		this.server_socket = new ServerSocket(this.server_port) ;// create server socket and bind it toserver port 
		System.out.println("server listening  on port "+this.server_port +"...");
	}
	/*method to launch server*/
	public void launch_server() throws IOException
	{
		while(listening)
		{
			this.client_socket = server_socket.accept();/* After a client connects, the ServerSocket creates a new Socket on an unspecified port
                                                         					and returns a reference to this new Socket*/
			/* input output streams */
			DataInputStream socket_in_stream = new DataInputStream(this.client_socket.getInputStream());
			DataOutputStream socket_out_stream = new DataOutputStream(this.client_socket.getOutputStream());

			/* read and print the  name of client that just connected to server along with the time of connection*/
			just_connected =socket_in_stream.readUTF(); 
			System.out.println(just_connected  +" is online at "+ Server.showTime());
			socket_out_stream.writeUTF("welcome "+ just_connected);
		}
		
	}
	
	/*display time*/
	public static  String showTime()
	{
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
		String formattedDate=dateFormat.format(date);
		return formattedDate;
		
	}
	public static void main(String[]args) throws IOException
	{
		Server s = new Server();

		s.launch_server();

	}
}
