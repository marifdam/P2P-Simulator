package main;

import java.io.IOException;
import java.util.Scanner;

import blockchain.Block;
import blockchain.Blockchain;
import p2p.*;
import user.User;
public class main {

	public static void main(String[] args) throws IOException {
		
			Block block = new Block("0x200",new java.util.Date(), "<transactions>");
				menu();
			
		}

	
	
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		String opc;
		System.out.println("Menu");
		System.out.println("1- Adicionar novo usuario");
		System.out.println("2- Gerar novo codigo");
		System.out.println("3- Validar codigo");
		opc = sc.nextLine();
		switch(opc) {
		case "1":
			User user = new User();
			System.out.println("Anote estes dados.");
			user.newUser();
			user.writeKeys();
			sc.reset();
			menu();
		
		case "2":
			System.out.println("Insira sua chave privada:");
			String privateKey = sc.nextLine();
			
		}
		
	}

}
