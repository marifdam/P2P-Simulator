package p2p;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;

import javax.json.Json;

public class Peer {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Entre com nome e porta para o par:");
		String[] values = bf.readLine().split(" ");
		ServerThread serverThread = new ServerThread(values[1]);
		serverThread.start();
		new Peer().updateListenToPeers(bf,values[0],serverThread);
	}
	
	public void updateListenToPeers(BufferedReader br, String username, ServerThread st) throws Exception{
		System.out.println("Enter hostname:");
		System.out.println(" Peers to recieve messages from: (s to skip)");
		String input = br.readLine();
		String[] inputValues = input.split(" ");
		
		if(!input.equals("s") ) {
			for (int i =0; i<inputValues.length; i++) {
			String[] address = inputValues[i].split(":");
			Socket socket = null;
			
			try {
				socket = new Socket(address[0],Integer.valueOf(address[1]));
				new PeerThread(socket);
				
				
			}catch(Exception e) {
				if(socket != null) {
					socket.close();
				}
				else {
					System.out.println("invalid input");
				}
			}
		}
	}
		
	}
	
	public void communicate(BufferedReader br,String username,ServerThread st) {
		try {
			System.out.println("Voce pode se comunicar, e para sair e c para mudar");
			boolean flag = true;
			while(flag) {
				String message = br.readLine();
				
				if(message.equals("e")) {
					flag = false;
					break;
				}else if (message.equals("c")){
					updateListenToPeers(br,username,st);
					
				}else{
					
					StringWriter sw = new StringWriter();
					Json.createWriter(sw).writeObject(Json.createObjectBuilder()
							.add("username", username).add("message", message).build());
					st.sendMessage(sw.toString());
			}
			}
			System.exit(0);
		}catch(Exception e) {
			
		}
	}
}
