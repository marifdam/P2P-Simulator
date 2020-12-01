package blockchain;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import rsa.RSA;

/**
 * BlockChain em si
 * @author mari
 *
 */
public class Blockchain {
	String privateRandom;
	String publicRandom;
	String random;
	
	private List<Block> chain;
	
	public Blockchain() {
		chain = new ArrayList<Block>();
		chain.add(generateGenesis());
	}
	
	private Block generateGenesis() {
		Block genesis = new Block(0,new java.util.Date(),"<transactions>");
		genesis.setPreviousHash(null);
		genesis.computeHash();
		return genesis;
	}
	
	public void addBlock(Block blk) {
		Block newBlock = blk;
		newBlock.setPreviousHash(chain.get(chain.size()-1).getHash());
		newBlock.computeHash();
		this.chain.add(newBlock);
		
	}
	
	public void displayChain() {
		for(int i=0; i<chain.size(); i++) {
			System.out.println("Block: "+ i);
			System.out.println("Version: "+ chain.get(i).getversion());
			System.out.println("Timestamp: "+chain.get(i).getTimestamp());
			System.out.println("PreviousHash: "+chain.get(i).getPreviousHash());
			System.out.println("Hash: "+chain.get(i).getHash());
			System.out.println();
		}
	}
	
	public Block getLatestBlock() {
		return this.chain.get(chain.size()-1);
	}
	
	public void isValid() throws Throwable { //alterações de throws
		for(int i=chain.size()-1; i>0;i--) {
			if(!(chain.get(i).getHash().equals(chain.get(i).computeHash()))) {
				System.out.println("Chain is not valid");
				return;
			}
			
			if (!(chain.get(i).getPreviousHash().equals(chain.get(i-1).computeHash()))) {
				System.out.println("Chain is not valid");
				return;
			}
			
			//alterações
			KeyPair pair;
			
			pair = RSA.generateKeyPair();
			
			privateRandom = RSA.encrypt(random, pair.getPublic());
			publicRandom = RSA.decrypt(random, pair.getPrivate());
			
			if(!(random.equals(publicRandom))) {
				System.out.println("Chain is not valid / Authentication fault");
				
			}
			
		}
		
		System.out.println("Chain is valid");
	}
	
	public void fileKeys(String code, int i) {
		try(BufferedWriter bf = new BufferedWriter(new FileWriter("codes"+i+".txt"))){
			bf.write(code);
			bf.newLine();
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	public void users(String pubkey,int i) {
		try(BufferedWriter bf = new BufferedWriter(new FileWriter("users"+i+".txt"))){
			bf.write(pubkey);
			bf.newLine();
		}catch(IOException e) {
			e.getMessage();
		}
	}
}

			
			
				
			
		
	



