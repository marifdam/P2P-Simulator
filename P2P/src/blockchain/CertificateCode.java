package blockchain;

import rsa.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import Decoder.BASE64Decoder;
import key.*;

public class CertificateCode {
	private static KeyPair pair;

	public void keypairUser() throws Exception {
		pair = RSA.generateKeyPair();
	}

	public void certificateCode() throws Exception {
		PublicKey pubkey = pair.getPublic();
		PrivateKey privKey = pair.getPrivate();
		String code = Random.randomString(20);
		writeCode(code);
		System.out.println("Codigo sem encriptar:\n" + code);
		System.out.println("Seu codigo nao encriptado e as chaves estao no arquivo code," + "publickey e privatekey.");
		String newCode = RSA.encrypt(code, pubkey);

		try {
			PemFile(pubkey, "PUBLIC KEY", "publickey");
			PemFile(privKey, "PRIVATE KEY", "privatekey");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("code.txt"))) {
			bw.write(newCode);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PemFile(Key key, String desc, String filename) throws IOException {
		final PemObject pemObj;
		pemObj = new PemObject(desc, key.getEncoded());

		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		try {
			pemWriter.writeObject(pemObj);
		} finally {
			pemWriter.close();
		}

	}

	public void decodeCode() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("code.txt"));
			BufferedReader bf = new BufferedReader(new FileReader("originalCodes.txt"));
			String lines = br.readLine();
			String[] fields = lines.split(" ");
			String code = fields[0];
			List<String> comparator = new ArrayList<>();
			File f = new File("privatekey");
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] keyBytes = new byte[(int) f.length()];
			dis.readFully(keyBytes);
			dis.close();

			String temp = new String(keyBytes);
			String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----", "");
			privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");

			BASE64Decoder b64 = new BASE64Decoder();
			byte[] decoded = b64.decodeBuffer(privKeyPEM);

			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey p = kf.generatePrivate(spec);

			String original = RSA.decrypt(code, p);
			System.out.println("Codigo original:\n" + code);
			System.out.println("\nCodigo decriptado:\n" + original);

			
			String bflines = bf.readLine();

			while (bflines != null) {
				String compare = bflines;
				comparator.add(bflines);
				bflines = bf.readLine();
			}
			System.out.println(comparator.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeCode(String code) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("originalCodes.txt", true))) {
			bw.write(code);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
