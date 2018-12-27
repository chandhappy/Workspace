import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class DrivesListingExample {
	public static final String storageConnectionString = 
	        "DefaultEndpointsProtocol=http;" + 
	        "AccountName=dcpkgn2014;" + 
	        "AccountKey=Jk1noVG8EPe/058Suz0SDtUei5Ova8Xe6w+CxVRGmHaefk5JHv6fE3SuXt4rjTRhms3K2PKMZlSjZS/raxKlIg==";
 
    public static void main(String[] args) {
    	UploadImagesToAzure();
    	System.exit(0);
        FileSystemView fsv = FileSystemView.getFileSystemView();
         System.out.println(new File("resources/archive/").getAbsolutePath());
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
               if(fsv.getSystemDisplayName(aDrive).substring(5).equals("ESD_ISO"))
            	   {
            	   String path=aDrive+"\\support\\";
            	   File dir = new File(path);
            	   displayDirectoryContents(dir);
            	   break;
            	   }
            }
        }
        
    }

	private static void displayDirectoryContents(File dir) {
		// TODO Auto-generated method stub
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					String destination = "C:\\Users\\INCBASHA\\Desktop\\";
					
			        File destDir = new File(destination);
			        String source = file.toString();
			        File srcDir = new File(source);
			        
					FileUtils.copyDirectoryToDirectory(srcDir,destDir);
				} 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void UploadImagesToAzure(){
		 try{
			 String url = "C:\\Users\\INCBASHA\\Desktop\\Testuploadtool1.jpg";
			 
             // Retrieve storage account from connection-string
                CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
                
                // Create the blob client
                CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

                // Get a reference to a container
                // The container name must be lower case
                CloudBlobContainer container = blobClient.getContainerReference("test123");
               
                // Create the container if it does not exist
                container.createIfNotExists();
                
                // Create a permissions object
                BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
                // Include public access in the permissions object
                containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
                // Set the permissions on the container
                container.uploadPermissions(containerPermissions);
                          

                // Create or overwrite the "myimage.jpg" blob with contents from a local file
                CloudBlockBlob blob = container.getBlockBlobReference("tool/Testuploadtool2.jpg");
                File source = new File(url);
                blob.upload(new FileInputStream(source), source.length());
                System.out.println(blob.getStorageUri().getPrimaryUri());
                }
                catch(Exception e){
                	e.printStackTrace();
                }
	}
	
}