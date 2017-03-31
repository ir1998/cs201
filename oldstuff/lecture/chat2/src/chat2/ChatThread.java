package chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread {
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	private ChatServer cs;
	public ChatThread(Socket s, ChatServer cs){
		this.s = s;
		this.cs = cs;
		try{
			br= new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			this.start();
		}catch(IOException ioe){
			System.out.println("ioe in ChatThread: "+ioe.getMessage());
		}
		
		
	}
	public void run(){
		try{
			while(true){
				String line = br.readLine();
				cs.sendMessageToAllClients(line);
			}
			
		}catch(IOException ioe){
			System.out.println("ioe in ChatThread.run(): "+ioe.getMessage());
		}
	}
	public void sendMessage(String message){
		pw.println(message);
		pw.flush();
	}
}
