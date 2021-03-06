package co.com.jakaboy.login.commons.controller.response

trait Response

final case class MessageResponse( status: Int, message: String ) extends Response

final case class DateTimeResponse( fecha: String, hora: String ) extends Response

