
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myString =  "ABC";
		if (myString.matches("[a-zA-Z]*")) {
		    System.out.println("Alphanumeric");
		}
		else {
		    System.out.println("Not alphanumeric");
		}
		
		if (isAlphanumeric(myString)) {
		    System.out.println("Alphanumeric");
		}
		else {
		    System.out.println("Not alphanumeric");
		}
		
	}
	
	static boolean isAlphanumeric(String str) {
	    for (int i=0; i<str.length(); i++) {
	        char c = str.charAt(i);
	        if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a)
	            return false;
	    }

	    return true;
	}

}
