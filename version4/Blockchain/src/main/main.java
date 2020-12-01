package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.CertificateCode;
import p2p.*;

public class main {

	public static void main(String[] args) throws IOException {
		try {
			menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		CertificateCode cert = new CertificateCode();
		String opc;
		cert.keypairUser();
		System.out.println("Menu");
		System.out.println("1- Gerar novo codigo");
		System.out.println("2- Validar codigo");
		System.out.println("3- Enviar arquivos");
		System.out.println("4- Receber arquivos");
		System.out.println("5- Dados do bloco");
		System.out.println("6- Sair");
		opc = sc.nextLine();
		switch (opc) {
		case "1":
			cert.certificateCode();
			sc.reset();
			System.out.println("\n");
			menu();
		case "2":
			cert.decodeCode();
			sc.reset();
			System.out.println("\n");
			menu();
		case "3":
			server server = new server();
			server.server();
			System.out.println("\n");
			menu();
		case "4":
			client client = new client();
			client.client();
			System.out.println("\n");
			menu();
		case "5":
			bloco();
			System.out.println("\n");
			menu();
		case "6":
			System.out.println("Aplicação fechada.");
			System.exit(0);

		}

	}

	public static void bloco() {
		try (BufferedReader bf = new BufferedReader(new FileReader("originalCodes.txt"))) {
			String lines = bf.readLine();
			Blockchain blockchain = new Blockchain();
			int count = 0;
			int countLines = 0;
			Block block = new Block(count, new java.util.Date(), "files");
			blockchain.addBlock(block);
			while (lines != null) {
				countLines++;
				if (countLines%100==0) {
					count++;
					blockchain.addBlock(new Block(count, new java.util.Date(), "files"));
					lines = bf.readLine();
				} else {
					lines = bf.readLine();
				}
				blockchain.displayChain();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
