
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{

    private static InetAddress server_ip ; //SERVER IP ADDRESS 
    private static  Socket client_socket;  // CLIENT SOCKET 
    private static final int server_port = 4446; // LISTENNING PORT  
    private static DataOutputStream out_stream;// input data stream
    private static DataInputStream in_stream;// output data stream
    private static String username;


   
    public Client(String name) throws IOException,UnknownHostException // constructor
    {
    	username = name;
    }
    /* establish communication*/
   
    public void startClient() throws IOException
    {
    	server_ip = InetAddress.getLocalHost();// server running on the same machine gets local host ip
        client_socket = new Socket(server_ip,server_port);// create client socket and request connection to server 
        System.out.println("connection to server successful");//this will print if connection is successfull 
        
        /* input and output streams of client_socket*/
        in_stream = new DataInputStream(client_socket.getInputStream()); //initiate the data input stream for the client socket
        out_stream = new DataOutputStream(client_socket.getOutputStream());//initiate the data output stream for the client socket
        
        /*write to server the name of the client that just connected */
        String str = Client.username;
        out_stream.writeUTF(str);/*write to the server's input stream the username using modified UTF-8 encoding*/
        /*read message from server */
        String msg = in_stream.readUTF();
        System.out.println("server replied: "+msg);
        
    }
    public static void main(String []args) throws UnknownHostException, IOException
    {
        Client c1 = new  Client("CLIENT 1");
        c1.startClient();
    }

}