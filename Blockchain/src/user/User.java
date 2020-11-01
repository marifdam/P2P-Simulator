package user;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import rsa.*;
/**
 * Criação de novos usuarios
 * @author mari
 *
 */
public class User {
	private static KeyPair pair;
	
	public void keyPairUser() throws Exception {
		pair = RSA.generateKeyPair();
	}

	public static String publicKey() {
		return  pair.getPublic().toString();
	}
	
	public static String privateKey() {
		return pair.getPrivate().toString();
	}
	
	public void newUser() {
		System.out.println("Your public Key: "+ publicKey());
		System.out.println("Your private Key: "+ privateKey());
	}
	
}
