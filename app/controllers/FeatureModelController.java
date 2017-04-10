package controllers;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.FeatureModel;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import util.AWSS3;
import util.Util;

public class FeatureModelController extends Controller {

	/**
	 * Create a new feature model in database and
	 * upload the feature model file to AWS S3.
	 * @return Result with the new created feature 
	 * model as JSON.
	 */
	public Result createFeatureModel(){
		try{
			boolean uploaded;
			Form<FeatureModel> form = Form.form(FeatureModel.class).bindFromRequest();
			MultipartFormData<File> body = request().body().asMultipartFormData();
			FilePart<File> filePart = body.getFile("file");
			File file = filePart.getFile();
			
			if(form.hasErrors()) {
				return badRequest(Util.createResponse("Json error.", false));
			}
			
			FeatureModel featureModel = new FeatureModel();
			featureModel.owner = form.get().owner;
			featureModel.ownerEmail = form.get().ownerEmail;
			featureModel.name = form.get().name;
			featureModel.description = form.get().description;
			featureModel.extension = form.get().extension;
			featureModel.save();
			
			uploaded = AWSS3.uploadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureModel.id + "." + featureModel.extension, file);
			
			if(uploaded) {
				featureModel.file = Util.AWS_S3_URL + "/" + Util.BUCKET_MAIN_COCO + "/" + Util.BUCKET_COCO_MODELS + featureModel.id + "." + featureModel.extension;
				featureModel.update();
				
				JsonNode json = Json.toJson(featureModel);
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
	 * Get the list of feature models in data base.
	 * @return Result with feature models as JSON.
	 */
	public Result getFeatureModels(){
		List<FeatureModel> featureModels = FeatureModel.find.all();
		JsonNode json = Json.toJson(featureModels);
		return created(Util.createResponse(json, true));
	}
	
	/**
	 * Get a feature model given its ID.
	 * @return Result with  the feature model as JSON.
	 */
	public Result getFeatureModelById(String id){
		long integerId = Integer.parseInt(id);
		FeatureModel featureModel = FeatureModel.find.byId(integerId);
		JsonNode json = Json.toJson(featureModel);
		return created(Util.createResponse(json, true));
	}
}
