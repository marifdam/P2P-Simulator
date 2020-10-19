package key;

public class Random {
	 
	public static String randomString(int n) { 
	 String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	 StringBuilder sb = new StringBuilder(n); 
	  
	 for (int i = 0; i < n; i++) { 
	      int index = (int)(randomString.length()* Math.random()); 
	      sb.append(randomString.charAt(index)); 
	            
	        } 
	      return sb.toString(); 
	        
	    } 
}
