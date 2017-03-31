package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
	public static void main(String [] args){
		try{
			Socket s = new Socket("localhost", 6789);
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			pw.println("Hello world!");
			pw.flush();
			String line = br.readLine();
			System.out.println("line received from server: "+line);
			pw.close();
			br.close();
			s.close();
			
		}catch(IOException ioe){
			System.out.println("ioe: "+ioe.getMessage());
		}
	}
}
