package util;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class Util {

	public static final String AFM2COCO_MODEL_EXTENSION = ".afm2coco";
	public static final String FAMA_MODEL_EXTENSION = ".afm";
	public static final String COCO_MODEL_EXTENSION = ".xmi";
	
	public static final String AWS_S3_URL = "https://s3-us-west-2.amazonaws.com";
	public static final String BUCKET_MAIN_COCO = "coco-backend";
	public static final String BUCKET_COCO_MODELS = "coco/";
	public static final String BUCKET_FAMA_MODELS = "fama/";
	
	public static final String COCO_MODEL_PATH = "models/";
	public static final String JAVA_MODEL_PATH = "app/generated/";
	
	/**
	 * Taken from: http://www.baeldung.com/rest-api-with-play
	 * @param response
	 * @param correct
	 * @return
	 */
	public static ObjectNode createResponse(Object response, boolean correct){
		ObjectNode result = Json.newObject();
		result.put("isSuccessfull", correct);
		
		if(response instanceof String){
			result.put("body", (String) response);
		}
		else{
			result.put("body", (JsonNode) response);
		}
		
		return result;
	}
	
	public static boolean waitUntilFileIsCreated(File file){
		boolean created = file.renameTo(file);
		System.out.println("FAMA! File: " + created );
		try {
			while(!created){
				Thread.sleep(1000);
				System.out.println("Waiting for " + file.getName() + " creation...");
				created = file.renameTo(file);
			}
			
			created = true;
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		return created;
	}
}
