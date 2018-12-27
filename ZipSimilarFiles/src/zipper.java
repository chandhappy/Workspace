import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.attribute.BasicFileAttributes;

public class zipper {
	static File folder = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2");
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//File folder = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				//System.out.println(listOfFiles[i].getName());
				String target = listOfFiles[i].getName();
				for (int j = 0; j < target.length(); j++) {
					if (target.charAt(j) == '(') {
						String dirname = target.substring(0, j);
						File zip = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+dirname);
						if (!zip.exists()) {
							zip.mkdir();
						}
						File source = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+listOfFiles[i].getName());
						File destination = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+dirname+"\\"+listOfFiles[i].getName()) ;
						source.renameTo(destination);
					}
				}
			} else if (listOfFiles[i].isDirectory()) {
			}
		}
		ZipDirectory();
	}
	
	static void ZipDirectory() throws Exception{
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				System.out.println(listOfFiles[i].getName());
				String folderToZip = "C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+listOfFiles[i].getName();
				String zipName = "C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+listOfFiles[i].getName()+".zip";
				zipFolder(Paths.get(folderToZip), Paths.get(zipName));
			}
		File tempdir = new File("C:\\Users\\INCBASHA\\Desktop\\Workingset2\\"+listOfFiles[i].getName());
		deleteFolder(tempdir);
		}
	}
	public static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	 private static void zipFolder(final Path sourceFolderPath, Path zipPath) throws Exception {
		 
		 	        final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		 	        Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
		 	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		 	                zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
		 	                Files.copy(file, zos);
		 	                zos.closeEntry();
		 	                return FileVisitResult.CONTINUE;
		 	            }
		 	        });
		 	        zos.close();
		 	    }
}
