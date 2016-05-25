package co.com.jakaboy.login.users.controller.response

import co.com.jakaboy.login.commons.controller.response.{ MessageResponse, ResponseFactory }
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results._

/**
 * Created by Usuario on 27/04/2016.
 */
object UserResponseFactory extends ResponseFactory {

  import UserSerializer._

  val NOTAUTHORIZED = 401

  def createOk( list: List[ UserResponse ] ): Result = Ok( Json.toJson( list ) )

  def createOk( user: UserAuthenticatedResponse ): Result = Ok( Json.toJson( user ) )

  def createUnauthorized(): Result = Unauthorized( Json.toJson( MessageResponse( NOTAUTHORIZED, "unauthorized user" ) ) )

}
