package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import coco.generators.CSPGenerator;
import coco.generators.IGenerator;
import models.FeatureModel;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.AWSS3;
import util.Util;

public class ConfiguratorController extends Controller {
	
	public Result generateConfiguratorById(String id){
		boolean downloaded;
		long integerId = Integer.parseInt(id);
		FeatureModel featureModel = FeatureModel.find.byId(integerId);
		
		downloaded = AWSS3.downloadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureModel.id + Util.COCO_MODEL_EXTENSION, 
				Util.COCO_MODEL_PATH + featureModel.id + Util.COCO_MODEL_EXTENSION);
		
		if(downloaded) {
			IGenerator generator = new CSPGenerator();
			generator.generateConfigurationProgram(Util.COCO_MODEL_PATH + featureModel.id + Util.COCO_MODEL_EXTENSION, 
					Util.JAVA_MODEL_PATH + "CSPModel.java");
			generator.runConfigurationProgram();
			
			JsonNode json = Json.toJson("OK");
			return created(Util.createResponse(json, true));
		}
		else{
			return badRequest(Util.createResponse("AWS S3 downloading file error.", false));
		}
	}
}