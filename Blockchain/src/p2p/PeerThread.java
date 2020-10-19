package p2p;

import javax.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public class PeerThread {
	private BufferedReader br;
	
	public PeerThread(Socket socket) throws IOException{
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() {
		boolean flag = true;
		while(flag) {
			try {
				JsonObject jo = Json.createReader(br).readObject();
				if(jo.containsKey("username")) {
				System.out.println("["+jo.getString("username")+"]: "+jo.getString("message"));
				}
				
			}catch(Exception e) {
				flag = false;
				
			}
		}
	}
}
