package DataConnectionThread;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;


public class UploadFile {

	public static void main(String qargs[]) throws IOException
	{
	Path csvDate= Paths.get("C:\\Users\\siddhartha.jain\\Desktop\\m.csv");
	String fileName="m";
	String dirName="woomaniya";
	String bucketName="logmanagement";
	String name = new UploadFile().uploadFileUsingGCSClient(csvDate, fileName, dirName, bucketName);
	
	System.out.println(name);
	}

	public String uploadFileUsingGCSClient(Path csvDate,
			  String fileName, String dirName,String bucketName) throws IOException {
			  System.out.println("start");
			   // init the bucket access
			    GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
			    System.out.println("start aftergcs");
			    GcsFilename filename = new GcsFilename(bucketName+"/"+dirName, fileName);
			    System.out.println("start afterfilename");
			    GcsFileOptions fileOptions = new GcsFileOptions.Builder()
			           .mimeType("application/CSV")          
			           .acl("public-read")
			           //.addUserMetadata("myfield1", "my field value")
			           .build();
			    System.out.println("start after file options");
			    GcsOutputChannel outputChannel = gcsService.createOrReplace(filename, fileOptions);
			    System.out.println("start afteroutputchannel");
			    // write file using this stream
			    BufferedOutputStream outStream = new BufferedOutputStream(Channels.newOutputStream(outputChannel));
			    System.out.println("start afteroutstream");
			    // read the input stream
			    byte[] buffer = new byte[1024];
			    //List<byte[]> allBytes = new LinkedList<byte[]>();
			    InputStream reader = new ByteArrayInputStream(Files.readAllBytes(csvDate));
			    while(true) {
			        int bytesRead = reader.read(buffer);
			        //log.info("bytesRead:"+bytesRead);
			        if (bytesRead == -1) {
			            break; // have a break up with the loop.
			        } else if (bytesRead < 1024) {
			            byte[] temp = Arrays.copyOf(buffer, bytesRead);
			            outStream.write(temp);
			           
			        } else {
			         outStream.write(buffer);
			        }
			    }

			    outStream.close();
			    System.out.println("start afteroutstream");
			    outputChannel.close();
			    System.out.println("start afteroutputchannel");
			    String uploadedFileURL="gs://"+bucketName+"/"+dirName+"/"+fileName;
			   // log.info("uploadedFileURL : "+uploadedFileURL);
			    return uploadedFileURL;
			  }

}
