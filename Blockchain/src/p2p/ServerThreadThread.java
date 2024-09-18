package p2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadThread {
	private ServerThread st;
	private Socket socket;
	private PrintWriter pw;
	
	public ServerThreadThread(Socket socket, ServerThread st) {
		this.socket = socket;
		this.st = st;
	}
	
	public void run() {
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		this.pw = new PrintWriter(socket.getOutputStream(),true);
		while(true) {
			st.sendMessage(br.readLine());
		}
		}catch(Exception e) {
			st.getServerThreadThreads().remove(this);
		}
	}

	public ServerThread getSt() {
		return st;
	}

	public void setSt(ServerThread st) {
		this.st = st;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	
	
}
