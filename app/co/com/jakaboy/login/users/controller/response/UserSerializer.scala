package co.com.jakaboy.login.users.controller.response

/**
 * Created by Usuario on 27/04/2016.
 */
import play.api.libs.json.{ Json, Writes }

object UserSerializer {

  implicit val userWrites: Writes[ UserResponse ] = Json.writes[ UserResponse ]

  implicit val userAuthenticatedWrites: Writes[ UserAuthenticatedResponse ] = Json.writes[ UserAuthenticatedResponse ]

}
