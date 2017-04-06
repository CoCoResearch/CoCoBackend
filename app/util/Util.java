package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class Util {

	public static final String BUCKET_MAIN_COCO = "coco-backend";
	public static final String AWS_S3_URL = "https://s3-us-west-2.amazonaws.com";
	
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
}
