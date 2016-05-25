package co.com.jakaboy.login.users.controller.response

/**
 * Created by Usuario on 27/04/2016.
 */
import co.com.jakaboy.login.commons.controller.response.Response

final case class UserResponse( id: Int, name: String, email: String, pass: String, phone: String ) extends Response

final case class UserAuthenticatedResponse( name: String, phone: String ) extends Response
