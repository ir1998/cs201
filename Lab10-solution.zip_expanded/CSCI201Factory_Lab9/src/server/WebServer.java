package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer implements Runnable {

	int portNumber;
	String docRoot;
	WebServerGUI owner;
	private static volatile boolean serverStatus;
	private ServerSocket serverSocket;

	public WebServer() {
		// Set the serverStatus to be false on startup, i.e. not running
		serverStatus = false;
	}

	@Override
	public void run() {
		// Continous loop waiting for a request
		while (true) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				synchronized (this) {
					while (getStatus() == false) {
						wait();
					}
				}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			try {
				socket = serverSocket.accept();
				// check to see if server is still active, as accept may have
				// returned because the port has been closed
				if (getStatus() == true) {
					input = socket.getInputStream();
					output = socket.getOutputStream();
					HTTPRequest request = new HTTPRequest(input);
					int parsecode = request.parseEntireRequest();
					if (parsecode > 0) {
						owner.setStatusMessage(request.getErrorMessage() + "\n");
					} else {
						// Continue and process the HTTP Request.
						// Create a new HTTPResponse object and set the output stream
						// and the owning server accordingly.
						HTTPResponse response = new HTTPResponse(output, this);
						// Return an error if not a GET request
						if (request.getMethod().compareTo("GET") == 0) {
							// GET request, so pass to Response object for further processing
							response.setRequest(request);
						} else {
							// At this stage the server only handles GET requests
							// Return a 501 Method Not Supported error.
							response.returnError(501);
						}
					}
				}

				// Close the socket
				socket.close();
			} catch (Exception e) {
				// Exception thrown legally if socket has been closed whilst accept() is open
				// i.e. if server has been stopped by the user.
				// if this isn't the case then print the stacktrace
				if (getStatus() == false) {
					System.out.println(e.getMessage());
				} else {
					e.printStackTrace();
				}
				continue;
			}
			// if all is OK, then the loop re-starts and the server will continue
			// to listen and respond.
		}
	}

	public synchronized void startServer(WebServerGUI owningUI, int port, String docroot) {
		// Set the server operating parameters
		setOwner(owningUI);
		setPort(port);
		setDocRoot(docroot);
		String message = "Starting server on port " + Integer.toString(port) + "...";
		owner.setStatusMessage(message);
		// Start the server, or at least try to...
		// Open the SocketServer on the specified port
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		this.serverStatus = true;
		serverNotify();
	}

	public void stopServer() {
		try {
			serverStatus = false;
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private synchronized void serverNotify() {
		notify();
	}

	private void setPort(int port) {
		portNumber = port;
	}

	public int getPort() {
		return portNumber;
	}

	private void setDocRoot(String docroot) {
		docRoot = docroot;
	}

	public String getDocRoot() {
		return docRoot;
	}

	private void setOwner(WebServerGUI owningUI) {
		owner = owningUI;
	}

	public boolean getStatus() {
		return serverStatus;
	}

}
