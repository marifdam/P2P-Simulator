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
			FileOutputStream fileOutputStream = new FileOutputStream("copia.txt");
			System.out.println("Aguardando envio...");
			long size = dataInputStream.readLong(); // read file size
			byte[] buffer = new byte[4 * 1024];
			while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
				fileOutputStream.write(buffer, 0, bytes);
				size -= bytes; // read upto file size
			}
			compareFile();
			fileOutputStream.close();
			dataInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compareFile() {
		try {
			File file = new File("copia.txt");
			File file2 = new File("originalCodes.txt");
			BufferedReader bf = new BufferedReader(new FileReader(file));
			BufferedReader br = new BufferedReader(new FileReader(file2));
			String line1 = bf.readLine();
			String line2 = br.readLine();

			while (line1 != null && line2 != null) {
				if (!line1.contains(line2)) {
					System.out.println("Arquivo adulterado, transferencia encerrada.");
					file.delete();
				}
			
				line1 = bf.readLine();
				line2 = br.readLine();
			}
			file2.renameTo(new File("originalCodes.txt"));
			System.out.println("Arquivo recebido.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
