package blockchain;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
/**
 * Cria um novo bloco
 * @author mari
 *
 */
public class Block {
	
	private int version;
	private Date TimeStamp;
	private String hash;
	private String previousHash;
	private String data;
	private String signature;
	
	public Block(int version, Date timestamp, String data) {
		this.version = version;
		this.TimeStamp = timestamp;
		this.data = data;
		this.hash = computeHash();
	}
	
	public String computeHash() {
		
		String dataToHash = " " + this.version + this.TimeStamp + this.previousHash + this.data;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.hash = encoded;
		return encoded;
	}
	
	public int getversion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public Date getTimestamp() {
		return TimeStamp;
	}
	
	public void setTinestanp(Date timestamp) {
		TimeStamp = timestamp;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getPreviousHash() {
		return previousHash;
	}
	
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}
