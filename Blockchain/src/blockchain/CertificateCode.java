package blockchain;
import rsa.*;
import key.*;
public class CertificateCode {
	private String key;
	private String encodedKey;
	private String decodedKey;
	
	public void encodedCode() throws Throwable {
		key = Random.randomString(20);
		encodedKey = KeyPair.encoded(key);
		decodedKey = KeyPair.decoded(key);
	}
}
