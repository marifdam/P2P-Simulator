package p2p;

import java.net.*;

import javax.swing.JFileChooser;

import java.io.*;

public class server {
	public void server() {
		DataOutputStream dataOutputStream = null;
		try {
			ServerSocket socket = new ServerSocket(6666);
			System.out.println("Aguardando receptor...");
			Socket s = socket.accept();
			dataOutputStream = new DataOutputStream(s.getOutputStream());
			int bytes = 0;
			File file = new File("originalCodes.txt");
			FileInputStream fileInputStream = new FileInputStream(file);
			System.out.println("Enviando arquivo...");
			// send file size
			dataOutputStream.writeLong(file.length());
			// break file into chunks
			byte[] buffer = new byte[4 * 1024];
			while ((bytes = fileInputStream.read(buffer)) != -1) {
				dataOutputStream.write(buffer, 0, bytes);
				dataOutputStream.flush();
			}
			System.out.println("Transferencia finalizada.");
			fileInputStream.close();
			dataOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
