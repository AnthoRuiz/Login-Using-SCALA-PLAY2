package co.com.jakaboy.login.users.controller.request

/**
 * Created by Usuario on 27/04/2016.
 */
import play.api.libs.json.Json

object UserDeserializer {

  implicit val createUsuarioReads = Json.reads[ CreateUserRequest ]

  implicit val updateUsuarioReads = Json.reads[ UpdateUserRequest ]

  implicit val authenticateUsuarioReads = Json.reads[ AuthenticateUserRequest ]
}