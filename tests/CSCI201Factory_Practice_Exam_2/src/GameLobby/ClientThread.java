package GameLobby;


class ClientThread extends Thread {
	private int id;
	public ClientThread(int id) {
		this.id = id;
	}
	public void run() {
		new GameClient(id);
	}
}