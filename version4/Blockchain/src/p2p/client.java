package p2p;

import java.net.*;
import java.io.*;

public class client {
	public void client() {
		DataInputStream dataInputStream = null;
		try {
			Socket socket = new Socket("192.168.1.173", 6666);
			dataInputStream = new DataInputStream(socket.getInputStream());
			int bytes = 0;
			FileOutputStream fileOutputStream = new FileOutputStream("originalCodes.txt");
			System.out.println("Aguardando envio...");
			long size = dataInputStream.readLong(); // read file size
			byte[] buffer = new byte[4 * 1024];
			while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
				fileOutputStream.write(buffer, 0, bytes);
				size -= bytes; // read upto file size
			}
			System.out.println("Arquivo recebido.");
			fileOutputStream.close();
			dataInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
