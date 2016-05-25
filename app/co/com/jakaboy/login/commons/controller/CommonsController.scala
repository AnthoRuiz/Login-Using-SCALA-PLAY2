package co.com.jakaboy.login.commons.controller

import co.com.jakaboy.login.commons.controller.response.{ DateTimeResponse, ResponseFactory }
import org.joda.time.DateTime
import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class CommonsController extends Controller {

  def getDate = Action.async {
    Future {
      val time = DateTime.now()
      val dateTime = DateTimeResponse( time.toString( "dd/MM/yyyy" ), time.toString( "hh:mm:ss" ) )
      ResponseFactory.createOk( dateTime )
    }
  }

}
