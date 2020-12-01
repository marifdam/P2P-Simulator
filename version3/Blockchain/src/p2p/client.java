package p2p;

import java.net.*;
import java.io.*;

public class client {
	public void client() {
		try {
			int filesize = 1022386;
			int bytesRead;
			int currentTot = 0;
			Socket socket = new Socket("192.168.1.173", 6066);
			System.out.println("Aguarde o envio do arquivo.");
			byte[] bytearray = new byte[filesize];
			InputStream is = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream("copy");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bytesRead = is.read(bytearray, 0, bytearray.length);
			currentTot = bytesRead;
			do {
				bytesRead = is.read(bytearray, currentTot, (bytearray.length - currentTot));
				if (bytesRead >= 0)
					currentTot += bytesRead;
			} while (bytesRead > -1);
			bos.write(bytearray, 0, currentTot);
			bos.flush();
			bos.close();
			socket.close();
			System.out.println("Transferencia finalizada.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
