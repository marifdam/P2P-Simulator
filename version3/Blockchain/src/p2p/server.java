package p2p;

import java.net.*;

import javax.swing.JFileChooser;

import java.io.*;

public class server {
	public void server() {
		try {
			ServerSocket serverSocket = new ServerSocket(6066);
			Socket socket = new Socket();
			System.out.println("Aguardando receptor...");
			socket = serverSocket.accept();
			System.out.println("Accepted connection : " + socket);

			JFileChooser fileChooser = new JFileChooser();

			int opt = fileChooser.showOpenDialog(null);
			File transferFile = null;
			if (opt == JFileChooser.APPROVE_OPTION) {
				System.out.println("Server on");
				transferFile = fileChooser.getSelectedFile();
			}

			byte[] bytearray = new byte[(int) transferFile.length()];
			FileInputStream fin = new FileInputStream(transferFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			bin.read(bytearray, 0, bytearray.length);
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Sending Files...");
			os.write(bytearray, 0, bytearray.length);
			os.flush();
			socket.close();
			System.out.println("File transfer complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
