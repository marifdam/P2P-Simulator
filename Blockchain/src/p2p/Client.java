package p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Client {
	private Socket socket;
	private ObjectOutputStream outputStream;

	public Client() throws IOException {
		this.socket = new Socket("localhost", 6066);
		this.outputStream = new ObjectOutputStream(socket.getOutputStream());

		new Thread(new ListenerSocket(socket)).start();
		menu();
	}

	private void menu() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome:");
		String nome = sc.nextLine();
		this.outputStream.writeObject(new FileMessage(nome));

		int opc = 0;

		while (opc != 1) {
			System.out.println("1-Sair\n2-Enviar");
			opc = sc.nextInt();
			if (opc == 2) {
				send(nome);

			} else if (opc == 1) {
				System.exit(0);
			}
		}

	}

	public void send(String nome) throws IOException {
		FileMessage fileMessage = new FileMessage();
		JFileChooser fileChooser = new JFileChooser();

		int opt = fileChooser.showOpenDialog(null);

		if (opt == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			this.outputStream.writeObject(new FileMessage(nome, file));
		}
	}

	private class ListenerSocket implements Runnable {
		private ObjectInputStream inputStream;

		public ListenerSocket(Socket socket) throws IOException {
			this.inputStream = new ObjectInputStream(socket.getInputStream());
		}

		@Override
		public void run() {
			FileMessage message = null;
			try {
				while ((message = (FileMessage) inputStream.readObject()) != null) {
					System.out.println("Voce recebeu um arquivo de " + message.getClient());
					System.out.println("O arquivo e " + message.getFile().getName());

					// imprime(message);
					salvar(message);
					System.out.println("1-Sair\n2-Enviar");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
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

	public void imprime(FileMessage message) {
		try {
			FileReader fileReader = new FileReader(message.getFile());
			BufferedReader bf = new BufferedReader(fileReader);
			String line;

			while ((line = bf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
