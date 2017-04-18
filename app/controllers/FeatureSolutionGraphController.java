package controllers;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import coco.modifiers.AddFaMaFMModifier;
import coco.modifiers.IModifier;
import coco.util.FileManager;
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
			File xmiFile;
			
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
			
			if(featureSolutionGraph.extension.equals("afm")) {
				String xmiPath = Util.COCO_MODEL_PATH + featureSolutionGraph.id + Util.COCO_MODEL_EXTENSION;
				String famaPath = Util.COCO_MODEL_PATH + featureSolutionGraph.id + Util.FAMA_MODEL_EXTENSION;
				File famaFile = new File(famaPath);
				
				FileManager manager = new FileManager();
				manager.copyFile(file, famaFile);
				Util.waitUntilFileIsCreated(famaFile);
				
				IModifier modifier = new AddFaMaFMModifier();
				modifier.modifyFSG(xmiPath, famaPath);
				xmiFile = famaFile;
				Util.waitUntilFileIsCreated(famaFile);
				
				featureSolutionGraph.extension = "xmi";
			}
			else {
				xmiFile = file;
			}
			
			uploaded = AWSS3.uploadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + Util.COCO_MODEL_EXTENSION, xmiFile);
			
			if(uploaded) {
				featureSolutionGraph.file = Util.AWS_S3_URL + "/" + Util.BUCKET_MAIN_COCO + "/" + Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + Util.COCO_MODEL_EXTENSION;
				featureSolutionGraph.update();
				
				JsonNode json = Json.toJson(featureSolutionGraph);
				return created(Util.createResponse(json, true));
			}
			else {
				return badRequest(Util.createResponse("AWS S3 uploading file error.", false));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
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
	
	/**
	 * Add a feature model to a feature solution graph,
	 * based on the id obtained as parameter. The file is
	 * updated in AWS S3. 
	 * @param id - 
	 * @return
	 */
	public Result addFeatureModelToFSGById(String id){
		Form<FeatureModel> form = Form.form(FeatureModel.class).bindFromRequest();
		MultipartFormData<File> body = request().body().asMultipartFormData();
		FilePart<File> filePart = body.getFile("file");
		File file = filePart.getFile();
		
		if(form.hasErrors()) {
			return badRequest(Util.createResponse("Json error.", false));
		}
		
		if(file == null) {
			return badRequest(Util.createResponse("No file error.", false));
		}
		
		long integerId = Integer.parseInt(id);
		boolean downloaded;
		FeatureModel featureSolutionGraph = FeatureModel.find.byId(integerId);
		String xmiPath = Util.COCO_MODEL_PATH + featureSolutionGraph.id + Util.COCO_MODEL_EXTENSION;
		String famaPath = Util.COCO_MODEL_PATH + featureSolutionGraph.id + Util.AFM2COCO_MODEL_EXTENSION;
		
		downloaded = AWSS3.downloadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + Util.COCO_MODEL_EXTENSION, 
				xmiPath);
		
		if(downloaded) {
			IModifier modifier = new AddFaMaFMModifier();
			modifier.modifyFSG(xmiPath, famaPath);
			
			boolean uploaded = AWSS3.uploadFile(Util.BUCKET_MAIN_COCO, Util.BUCKET_COCO_MODELS + featureSolutionGraph.id + "." + featureSolutionGraph.extension, file);
			
			if(uploaded) {
				JsonNode json = Json.toJson(featureSolutionGraph);
				return created(Util.createResponse(json, true));
			}
			else{
				return badRequest(Util.createResponse("AWS S3 uploading file error.", false));
			}
		}
		else{
			return badRequest(Util.createResponse("AWS S3 downloading file error.", false));
		}
	}
}
