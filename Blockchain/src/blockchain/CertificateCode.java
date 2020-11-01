package blockchain;
import rsa.*;
import key.*;
/**
 * Gerador de codigo do certificado
 * @author mari
 *
 */
public class CertificateCode {
	private String key;
	private String encodedKey;
	private String decodedKey;
	
	public String encodedCode() throws Throwable {
		key = Random.randomString(20);
		encodedKey = KeyPair.encoded(key);
		
		return encodedKey;
	}
	
}
