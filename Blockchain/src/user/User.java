package user;

import java.security.KeyPair;
import rsa.*;

public class User {
	private KeyPair pair;
	
	public void keyPairUser() throws Exception {
		pair = RSA.generateKeyPair();
	}
	
}
