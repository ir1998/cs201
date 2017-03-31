package chat2;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	
	private Vector<ChatThread> chatThreads;
	public ChatServer(int port){
		chatThreads = new Vector<ChatThread>();
		try{
			ServerSocket ss= new ServerSocket(port);
			while(true){
				System.out.println("Waiting for connection...");
				Socket s = ss.accept();
				System.out.println("Connection from " + s.getInetAddress());
				ChatThread ct = new ChatThread(s, this);
				chatThreads.add(ct);
			}
		}catch (IOException ioe){
			System.out.println("ioe in ChatServer: "+ioe.getMessage());
		}
	}
	
	public void sendMessageToAllClients(String message){
		System.out.println(message);
		for(ChatThread ct: chatThreads){
			ct.sendMessage(message);
			
		}
	}
	
	public static void main(String[] args){
		new ChatServer(6789);
	}
}
