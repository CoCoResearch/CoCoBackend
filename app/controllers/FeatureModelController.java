package controllers;

import java.io.File;
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

	public Result create(){
		try{
			Form<FeatureModel> form = Form.form(FeatureModel.class).bindFromRequest();
			MultipartFormData<File> body = request().body().asMultipartFormData();
			FilePart<File> filePart = body.getFile("file");
			File file = filePart.getFile();
			
			if(form.hasErrors()) {
				return badRequest(Util.createResponse("Json error.", false));
			}
			
			FeatureModel featureModel = new FeatureModel();
			featureModel.owner = form.get().owner;
			featureModel.name = form.get().name;
			featureModel.extension = form.get().extension;
			featureModel.save();
			
			AWSS3.uploadFile(Util.BUCKET_MAIN_COCO, featureModel.id + "." + featureModel.extension, file);
			featureModel.file = Util.AWS_S3_URL + "/" + Util.BUCKET_MAIN_COCO + "/" + featureModel.id + "." + featureModel.extension;
			featureModel.update();
			
			JsonNode json = Json.toJson(featureModel);
			return created(Util.createResponse(json, true));
		}
		
		catch(Exception e) {
			return badRequest(Util.createResponse("Json error: " + e.getMessage(), false));
		}
		
	}
}
