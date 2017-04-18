package controllers;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import coco.generators.CSPGenerator;
import coco.generators.IGenerator;
import models.FeatureModel;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import util.AWSS3;
import util.Util;

public class FeatureSolutionGraphController extends Controller {

	/**
	 * Create a new feature solution graph in database and
	 * upload the feature model file to AWS S3.
	 * @return Result with the new created feature 
	 * model as JSON.
	 */
	public Result createFeatureSolutionGraph(){
		try{
			boolean uploaded;
			Form<FeatureModel> form = Form.form(FeatureModel.class).bindFromRequest();
			MultipartFormData<File> body = request().body().asMultipartFormData();
			FilePart<File> filePart = body.getFile("file");
			File file = filePart.getFile();
			
			if(form.hasErrors()) {
				return badRequest(Util.createResponse("Json error.", false));
			}
			
			FeatureModel featureSolutionGraph = new FeatureModel();
			featureSolutionGraph.owner = form.get().owner;
			featureSolutionGraph.ownerEmail = form.get().ownerEmail;
			featureSolutionGraph.name = form.get().name;
			featureSolutionGraph.description = form.get().description;
			featureSolutionGraph.extension = form.get().extension;
			featureSolutionGraph.save();
			
			uploaded = AWSS3.uploadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + "." + featureSolutionGraph.extension, file);
			
			if(uploaded) {
				featureSolutionGraph.file = Util.AWS_S3_URL + "/" + Util.BUCKET_MAIN_COCO + "/" + Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + "." + featureSolutionGraph.extension;
				featureSolutionGraph.update();
				
				JsonNode json = Json.toJson(featureSolutionGraph);
				return created(Util.createResponse(json, true));
			}
			else {
				return badRequest(Util.createResponse("AWS S3 uploading file error.", false));
			}
		}
		
		catch(Exception e) {
			return badRequest(Util.createResponse("Json error: " + e.getMessage(), false));
		}
	}
	
	/**
	 * Get the list of feature solution graphs in data base.
	 * @return Result with feature models as JSON.
	 */
	public Result getFeatureSolutionGraphs(){
		List<FeatureModel> featureSolutionGraphs = FeatureModel.find.all();
		JsonNode json = Json.toJson(featureSolutionGraphs);
		return created(Util.createResponse(json, true));
	}
	
	/**
	 * Get a feature solution graph given its ID.
	 * @return Result with  the feature model as JSON.
	 */
	public Result getFeatureSolutionGraphById(String id){
		long integerId = Integer.parseInt(id);
		FeatureModel featureSolutionGraph = FeatureModel.find.byId(integerId);
		JsonNode json = Json.toJson(featureSolutionGraph);
		return created(Util.createResponse(json, true));
	}
	
	public Result getFeatureModelToFSGById(String id){
		long integerId = Integer.parseInt(id);
		boolean downloaded;
		FeatureModel featureSolutionGraph = FeatureModel.find.byId(integerId);
		
		downloaded = AWSS3.downloadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + "." + Util.COCO_MODEL_EXTENSION, 
				Util.COCO_MODEL_PATH + featureSolutionGraph.id + "." + Util.COCO_MODEL_EXTENSION);
		
		if(downloaded) {
			IGenerator generator = new CSPGenerator();
			generator.generateConfigurationProgram(Util.COCO_MODEL_PATH + featureSolutionGraph.id + "." + Util.COCO_MODEL_EXTENSION, 
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
