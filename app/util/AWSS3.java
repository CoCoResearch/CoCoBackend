package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;

public class AWSS3 {
	private static AmazonS3 s3client;
	
	public static AmazonS3 getS3Client(){
		if(s3client == null) {
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("key1", "key2");
			s3client = new AmazonS3Client(awsCredentials);
		}
		
		return s3client;
	}
	
	public static boolean uploadFile(String bucket, String fileName, File file){
		boolean uploaded = false;
		try {
			s3client = getS3Client();
			s3client.putObject(bucket, fileName, file);
			uploaded = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return uploaded;
	}
	
	public static boolean downloadFile(String bucket, String fileName, String targetFile){
		boolean downloaded = false;
		try {
			s3client = getS3Client();
			S3Object object = s3client.getObject(bucket, fileName);

			File file = new File(targetFile);
			InputStream is = object.getObjectContent();
			OutputStream os = new FileOutputStream(file);
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = is.read(bytes)) != -1){
				os.write(bytes, 0, read);
			}
			
			is.close();
			os.close();
			downloaded = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return downloaded;
	}
}
