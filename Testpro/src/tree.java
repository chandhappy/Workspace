import java.io.File;


public class tree {
	public static void main(String[] args) {
	    File sysdir = new File("C:/VISUSCOUT100i");
	    for(File file:sysdir.listFiles()) {
	        System.out.println(file.getName());
	    }
	}

}
