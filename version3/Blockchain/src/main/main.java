package main;

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
import java.util.Base64;
import java.util.Scanner;


import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import blockchain.Block;
import blockchain.Blockchain;
import blockchain.CertificateCode;
import p2p.*;

public class main {

	public static void main(String[] args) throws IOException {
		
			Block block = new Block("0x200",new java.util.Date(), "<transactions>");
			
			try {
			menu();
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InvalidKeySpecException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(NoSuchProviderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	}

}
}
