import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class ServerSocket {
	private static Vector<Session> sessionVector = new Vector<Session>();
	public Map <String, Boolean> table = new HashMap<String, Boolean>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("opening");
		sessionVector.add(session);
	}

	@OnMessage
	public void onMove(String move, Session session) {
		System.out.println("move: " + move);
		try {
			if(table.containsKey(move.trim()))
				for (Session s : sessionVector) {
					s.getBasicRemote().sendText("nope!!!");
				}
			else{
				table.put(move.trim(), true);
				//check if won, send different message here
				
				
				session.getBasicRemote().sendText("");
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
			close(session);
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("closing");
		sessionVector.remove(session);
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("error");
	}

}