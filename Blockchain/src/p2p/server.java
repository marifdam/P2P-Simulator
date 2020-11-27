package p2p;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class server {
	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, ObjectOutputStream> streamMap = new HashMap<String, ObjectOutputStream>();
	
	public server() {
		try {
			serverSocket = new ServerSocket(6066);
			System.out.println("Server on ");
			
			while(true) {
				socket = serverSocket.accept();
				new Thread(new ListenerSocket(socket)).start();
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ListenerSocket implements Runnable{
		private ObjectOutputStream outputStream;
		private ObjectInputStream inputStream;
		
		public ListenerSocket(Socket socket) throws IOException{
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.inputStream = new ObjectInputStream(socket.getInputStream());
		}
		@Override
		public void run() {
			FileMessage message = null;
			try {
				while((message = (FileMessage) inputStream.readObject()) != null){
					streamMap.put(message.getClient(), outputStream);
					if(message.getFile() != null) {
						for(Map.Entry<String, ObjectOutputStream> kv : streamMap.entrySet()) {
							if(!message.getClient().equals(kv.getKey())) {
								kv.getValue().writeObject(message);
							}
						}
					}
				}
			}catch(IOException e) {
				streamMap.remove(message.getClient());
				System.out.println(message.getClient()+"Desconectou");
				
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new server();
	}
}
