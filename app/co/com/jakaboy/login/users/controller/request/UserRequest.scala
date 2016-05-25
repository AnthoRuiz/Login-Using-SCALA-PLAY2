package co.com.jakaboy.login.users.controller.request

/**
 * Created by Usuario on 27/04/2016.
 */

final case class CreateUserRequest( name: String, email: String, pass: String, phone: String )

final case class UpdateUserRequest( id: Int, name: String, email: String, pass: String, phone: String )

final case class AuthenticateUserRequest( email: String, pass: String )