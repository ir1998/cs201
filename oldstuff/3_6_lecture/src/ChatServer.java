import java.io.IOException;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ws1")
public class ChatServer {
	
	private static Vector<Session> sessVect = new Vector<Session>();
	
	@OnOpen
	public void open(Session session){
		System.out.println("opening");
		sessVect.add(session);
	}
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println("received message: " + message);
		try {
			for(Session s : sessVect){
				s.getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			System.out.println("ioe: " + e.getMessage());
		}
	}
	@OnClose
	public void close(Session session){
		System.out.println("closing");
		sessVect.remove(session);
	}
	@OnError
	public void onError(Throwable error){
		System.out.println("error");
	}
}
