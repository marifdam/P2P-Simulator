package blockchain;
import rsa.*;

import java.security.PrivateKey;

import key.*;
/**
 * Gerador de codigo do certificado
 * @author mari
 *
 */
public class CertificateCode {
	
	public String certificadeCode() {
		String code = Random.randomString(20);
		return code;
	}
	
//	public void encript(String ) {
	//	PrivateKey privkey = (PrivateKey) privateKey;
//	}
	
}
