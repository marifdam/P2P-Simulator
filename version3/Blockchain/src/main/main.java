package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;


import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import blockchain.Block;
import blockchain.Blockchain;
import blockchain.CertificateCode;
import p2p.*;

public class main {

	public static void main(String[] args) throws IOException {
	try(BufferedReader bf = new BufferedReader(new FileReader("originalCodes.txt"))){
		String lines = bf.readLine();
		Blockchain blockchain = new Blockchain();
		int count =0;
		int countLines = 0;
		Block block = new Block(count,new java.util.Date(),"files");
		blockchain.addBlock(block);
		while(lines != null) {
			countLines++;
			if(countLines == 100) {
				count++;
				blockchain.addBlock(new Block(count,new java.util.Date(),"files"));
				menu();
				lines = bf.readLine();
			}else {
				menu();
				lines = bf.readLine();
			}
			
		}
	}catch(Exception e) {
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
		System.out.println("3- Sair");
		opc = sc.nextLine();
		switch(opc) {
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
			break;
	}

}
}
