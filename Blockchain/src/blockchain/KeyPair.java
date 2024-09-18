package blockchain;
import rsa.*;
/**
 * Codificacao dos codigos dos certificados
 * @author mari
 *
 */
public class KeyPair {
	private static java.security.KeyPair pair;

	public static void generateKeyPair() throws Throwable {
		pair = RSA.generateKeyPair();
	
	}
	
	public static String encoded(String msg) throws Throwable {
		String encoded = RSA.encrypt(msg, pair.getPublic());
		
		return msg;
	}
	
	public static String decoded(String msg) throws Throwable {
		String decoded = RSA.decrypt(msg, pair.getPrivate());
		
		return msg;
	}
	
}
