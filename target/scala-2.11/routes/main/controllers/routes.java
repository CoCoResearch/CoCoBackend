
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Lina8a/Documents/job/asistencia/code/coco-backend/conf/routes
// @DATE:Mon Apr 10 12:04:21 COT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseFeatureModelController FeatureModelController = new controllers.ReverseFeatureModelController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseGeneratorController GeneratorController = new controllers.ReverseGeneratorController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseFeatureModelController FeatureModelController = new controllers.javascript.ReverseFeatureModelController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseGeneratorController GeneratorController = new controllers.javascript.ReverseGeneratorController(RoutesPrefix.byNamePrefix());
  }

}
