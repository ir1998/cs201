package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String [] args){
		try{
			ServerSocket ss = new ServerSocket(6789);
			System.out.println("Waiting for connection");
			Socket s = ss.accept();
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			String line = br.readLine();
			System.out.println("received from client: "+line);
			pw.println("Thanks for connecting!");
			pw.flush();
			//ALWAYS FLUSH!!!!!!
			
			//usually close in finally block (after checking for null)
			pw.close();
			br.close();
			s.close();
			ss.close();
		}catch (IOException ioe){
			System.out.println("ioe: " + ioe.getMessage());
		}
		
	}
}
