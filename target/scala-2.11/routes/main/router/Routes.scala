
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Lina8a/Documents/job/asistencia/code/coco-backend/conf/routes
// @DATE:Fri Apr 07 12:00:44 COT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:9
  Assets_1: controllers.Assets,
  // @LINE:12
  FeatureModelController_2: controllers.FeatureModelController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:9
    Assets_1: controllers.Assets,
    // @LINE:12
    FeatureModelController_2: controllers.FeatureModelController
  ) = this(errorHandler, HomeController_0, Assets_1, FeatureModelController_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, FeatureModelController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """featureModels""", """controllers.FeatureModelController.createFeatureModel"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """featureModels""", """controllers.FeatureModelController.getFeatureModels"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """featureModels/""" + "$" + """id<[^/]+>""", """controllers.FeatureModelController.getFeatureModelById(id:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      """ An example controller showing a sample home page""",
      this.prefix + """"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_FeatureModelController_createFeatureModel2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("featureModels")))
  )
  private[this] lazy val controllers_FeatureModelController_createFeatureModel2_invoker = createInvoker(
    FeatureModelController_2.createFeatureModel,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FeatureModelController",
      "createFeatureModel",
      Nil,
      "POST",
      """ Feature model creation""",
      this.prefix + """featureModels"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_FeatureModelController_getFeatureModels3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("featureModels")))
  )
  private[this] lazy val controllers_FeatureModelController_getFeatureModels3_invoker = createInvoker(
    FeatureModelController_2.getFeatureModels,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FeatureModelController",
      "getFeatureModels",
      Nil,
      "GET",
      """ Feature models procurement""",
      this.prefix + """featureModels"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_FeatureModelController_getFeatureModelById4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("featureModels/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_FeatureModelController_getFeatureModelById4_invoker = createInvoker(
    FeatureModelController_2.getFeatureModelById(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FeatureModelController",
      "getFeatureModelById",
      Seq(classOf[String]),
      "GET",
      """ Feature models procurement""",
      this.prefix + """featureModels/""" + "$" + """id<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:9
    case controllers_Assets_versioned1_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:12
    case controllers_FeatureModelController_createFeatureModel2_route(params) =>
      call { 
        controllers_FeatureModelController_createFeatureModel2_invoker.call(FeatureModelController_2.createFeatureModel)
      }
  
    // @LINE:15
    case controllers_FeatureModelController_getFeatureModels3_route(params) =>
      call { 
        controllers_FeatureModelController_getFeatureModels3_invoker.call(FeatureModelController_2.getFeatureModels)
      }
  
    // @LINE:18
    case controllers_FeatureModelController_getFeatureModelById4_route(params) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_FeatureModelController_getFeatureModelById4_invoker.call(FeatureModelController_2.getFeatureModelById(id))
      }
  }
}
