
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object indexOld_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class indexOld extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Welcome to Play")/*12.25*/ {_display_(Seq[Any](format.raw/*12.27*/("""

    """),format.raw/*17.8*/("""
    """),_display_(/*18.6*/play20/*18.12*/.welcome(message, style = "Java")),format.raw/*18.45*/("""

""")))}),format.raw/*20.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/*
 * This template takes a single argument, a String containing a
 * message to display.
 */
object indexOld extends indexOld_Scope0.indexOld
              /*
                  -- GENERATED --
                  DATE: Tue Oct 04 19:20:38 COT 2016
                  SOURCE: C:/Users/Lina8a/Documents/job/asistencia/code/coco-backend/app/views/indexOld.scala.html
                  HASH: c00f1c05d68754c3b12a2a3ffaafe456cbad7d39
                  MATRIX: 840->95|952->112|981->308|1009->310|1041->333|1081->335|1114->464|1146->470|1161->476|1215->509|1248->512
                  LINES: 30->5|35->5|37->11|38->12|38->12|38->12|40->17|41->18|41->18|41->18|43->20
                  -- GENERATED --
              */
          