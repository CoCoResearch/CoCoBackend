
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Lina8a/Documents/job/asistencia/code/coco-backend/conf/routes
// @DATE:Fri Apr 07 12:00:44 COT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
