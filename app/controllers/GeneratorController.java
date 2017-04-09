package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import coco.generators.CSPGenerator;
import coco.generators.IGenerator;
import models.FeatureModel;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Util;

public class GeneratorController extends Controller {
	
	public Result generateConfiguratorById(String id){
		long integerId = Integer.parseInt(id);
		FeatureModel featureModel = FeatureModel.find.byId(integerId);
		
		IGenerator generator = new CSPGenerator();
		generator.generateConfigurationProgram("models/coco1.xmi", "models/Coco1.java");
		
		JsonNode json = Json.toJson("OK");
		return created(Util.createResponse(json, true));
	}
}
