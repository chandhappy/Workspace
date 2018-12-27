import java.io.File;


public class tree {
	static String workingDir =System.getProperty("user.dir");
	public static void main(String[] args) {
	    File sysdir = new File(workingDir+"/Resource/Archive/");
	    for(File file:sysdir.listFiles()) {
	        System.out.println(file.getName());
	    }
	}

}
