package p2p;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class server {
	private ServerSocket serverSocket;
	private Socket socket;
	private Map<String, ObjectOutputStream> streamMap = new HashMap<String, ObjectOutputStream>();
	
	public server() {
		try {
			serverSocket = new ServerSocket(6666);
			
				socket = serverSocket.accept();
				new Thread(new ListenerSocket(socket)).start();
				
			
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
					System.out.println("Voce recebeu um arquivo de " + message.getClient());
					System.out.println("O arquivo e " + message.getFile().getName());

					// imprime(message);
					salvar(message);
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
	
	
	public void salvar(FileMessage message) {
		try {

			long time = System.currentTimeMillis();
			Thread.sleep(new Random().nextInt(1000));
			FileInputStream inputStream = new FileInputStream(message.getFile());
			FileOutputStream outputStream = new FileOutputStream(time + message.getFile().getName());

			FileChannel fin = inputStream.getChannel();
			FileChannel fout = outputStream.getChannel();

			long size = fin.size();

			fin.transferTo(0, size, fout);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new server();
	}
}
