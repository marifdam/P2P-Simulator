package p2p;



import java.net.*;

import javax.swing.JFileChooser;

import java.io.*;

public class server {
	public static void main (String [] args ) throws IOException { 
		ServerSocket serverSocket = new ServerSocket(15123); 
		Socket socket = serverSocket.accept(); 
		System.out.println("Accepted connection : " + socket); 
		
		JFileChooser fileChooser = new JFileChooser();

		int opt = fileChooser.showOpenDialog(null);
		File transferFile = null;
		if (opt == JFileChooser.APPROVE_OPTION) {
			
		 transferFile = fileChooser.getSelectedFile();
		}
		
		
		byte [] bytearray = new byte [(int)transferFile.length()]; 
		FileInputStream fin = new FileInputStream(transferFile);
		BufferedInputStream bin = new BufferedInputStream(fin); 
		bin.read(bytearray,0,bytearray.length); 
		OutputStream os = socket.getOutputStream(); 
		System.out.println("Sending Files..."); 
		os.write(bytearray,0,bytearray.length); 
		os.flush(); socket.close(); 
		System.out.println("File transfer complete"); 
		}
	}



