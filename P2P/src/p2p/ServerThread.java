package p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();

	public ServerThread(String portnumb) throws IOException {
		serverSocket = new ServerSocket(Integer.valueOf(portnumb));
	}

	public void run() {
		try {
			while (true) {
				ServerThreadThread stt = new ServerThreadThread(serverSocket.accept(), this);
				serverThreadThreads.add(stt);
				start();
			}
		} catch (Exception e) {
			System.out.println("erro" + e.getMessage());
		}
	}

	public void sendMessage(String message) {
		try {
			serverThreadThreads.forEach(t -> t.getPw().println(message));

		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Set<ServerThreadThread> getServerThreadThreads() {
		return serverThreadThreads;
	}

	public void setServerThreadThreads(Set<ServerThreadThread> serverThreadThreads) {
		this.serverThreadThreads = serverThreadThreads;
	}

}
