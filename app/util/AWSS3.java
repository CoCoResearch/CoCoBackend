package util;

import java.io.File;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

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
		boolean uploaded;
		try {
			s3client = getS3Client();
			s3client.putObject(bucket, fileName, file);
			uploaded = true;
		}
		catch(Exception e){
			uploaded = false;
			e.printStackTrace();
		}
		
		return uploaded;
	}
	
}
