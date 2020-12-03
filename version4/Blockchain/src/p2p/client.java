package p2p;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class client {
	public void client() {
		DataInputStream dataInputStream = null;
		try {
			//ip do host 192.168.1.173
			Socket socket = new Socket("192.168.1.154", 6666);
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
			File f = new File("originalCodes.txt");
			if(f.exists() == true) {
			compareFile();
			}else {
				System.out.println("Transferencia concluida.");
				File r = new File("copia.txt");
				r.renameTo(new File("originalCodes.txt"));
			}
			fileOutputStream.close();
			dataInputStream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compareFile() {
		try {
			File file = new File("copia.txt");
			File file2 = new File("originalCodes.txt");
			List<String> one = new ArrayList<>();
			List<String> two = new ArrayList<>();
			BufferedReader bf = new BufferedReader(new FileReader(file));
			BufferedReader br = new BufferedReader(new FileReader(file2));
			String line1 = bf.readLine();
			String line2 = br.readLine();
			int count1 = 0;
			int count2 = 0;
			while(line1 != null) {
				count1++;
				one.add(line1);
				line1 = bf.readLine();
			}
			while(line2 != null) {
				count2++;
				two.add(line2);
				line2 = br.readLine();
			}
			boolean var = false;
			if(one.size() < two.size()) {
				for(String b : two) {
					for(String a : one) {
						if(a.equals(b)) {
							var = true;
						}else {
						System.out.println("Arquivo adulterado. Aplicacao fechada.");
						file.delete();
						System.exit(1);
					}
				}
			}
			}if(one.size()>two.size()) {
				for(String a : one) {
					for(String b: two) {
						if(b.equals(a)) {
							var = true;
						}else {
							System.out.println("Arquivo adulterado. Aplicacao fechada.");
							file.delete();
							System.exit(1);
						}
					}
				}
			}
			
			file.renameTo(new File("originalCodes.txt"));
			System.out.println("Transferencia concluida.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
