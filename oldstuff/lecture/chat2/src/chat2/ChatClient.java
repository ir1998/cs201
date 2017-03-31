package chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends Thread{
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	public ChatClient(String hostname, int port){
		try{
			this.s = new Socket(hostname, port);
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.pw = new PrintWriter(s.getOutputStream());
			this.start();
			Scanner scan = new Scanner(System.in);
			while(true){
				String line = scan.nextLine();
				this.pw.println("nkaimakis: "+line);
				this.pw.flush();
			}
		}catch(IOException ioe){
			System.out.println("IOE in ChatClient: "+ioe.getMessage());
		}
	}

	public void run(){
		try{
			while(true){
				String line = "";
				while(line != null && !line.equals("null")){
					line = br.readLine();
					System.out.println(line);
				}
			}
		}catch(IOException ioe){
			//error would be disconnected from server (br can't read another line)
			System.out.println("IOE in ChatClient.run(): "+ioe.getMessage());
		}
	}
	
	public static void main(String[] args){
		new ChatClient("192.168.1.143", 6789);
	}
}
