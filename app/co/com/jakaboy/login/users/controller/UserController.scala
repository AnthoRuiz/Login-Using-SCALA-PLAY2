package co.com.jakaboy.login.users.controller

import co.com.jakaboy.login.commons.service.{ OperationError, OperationFailed }
import co.com.jakaboy.login.users.controller.request._
import co.com.jakaboy.login.users.controller.response.{ UserResponse, UserResponseFactory }
import co.com.jakaboy.login.users.service.UserService
import com.google.inject.{ Inject, Singleton }
import play.api.Logger
import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

@Singleton
final class UserController @Inject() ( service: UserService ) extends Controller {

  import UserDeserializer._

  lazy val logger: Logger = Logger( "UserService" )

  def authenticate = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ AuthenticateUserRequest ].fold(
      error => {
        Future.successful( UserResponseFactory.createBadRequest( "Invalid fields provided" ) )
      },
      user =>
        service.authenticateUser( user ).map { result =>
          result.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UserResponseFactory.createUnauthorized()
              }
            },
            success => UserResponseFactory.createOk( success )
          )
        }
    )
  }

  def updateUser = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ UpdateUserRequest ].fold(
      error => {
        Future.successful( UserResponseFactory.createBadRequest( "Invalid fields provided" ) )
      },
      user =>
        service.updateUser( user ).map { result =>
          result.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UserResponseFactory.createNotFound( "Invalid parameter supplied" )
                case OperationError( _ ) => UserResponseFactory.
                  createInternalServerError( "Error while the request is processed" )
              }
            },
            success => UserResponseFactory.createOk( "User updated successfully" )
          )
        }
    )
  }

  def getUser = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.getUser.map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => UserResponseFactory.createNotFound( "failed to consult users" )
            case OperationError( _ )  => UserResponseFactory.createInternalServerError( "Error while the request is processed" )
          }
        },
        users => UserResponseFactory.createOk( users.map( user => UserResponse( user.id, user.name, user.email, user.pass,
          user.phone ) ) )
      )
    }
  }

  def createUser = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ CreateUserRequest ].fold(
      error => {
        Future.successful( UserResponseFactory.createBadRequest( "Invalid parameter supplied" ) )
      },
      usuario =>
        service.createUser( usuario ).map { reult =>
          reult.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UserResponseFactory.createNotFound( "Invalid parameter supplied" )
                case OperationError( x )  => UserResponseFactory.createBadRequest( x )
              }
            },
            success => UserResponseFactory.createOk( "user created successfully" )
          )
        }
    )
  }

  def deleteUser( id: Int ) = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.deleteUser( id ).map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => UserResponseFactory.createNotFound( "Invalid parameter supplied" )
            case OperationError( _ )  => UserResponseFactory.createInternalServerError( "Error while the request is processed" )
          }
        },
        success => UserResponseFactory.createOk( "User successfully deleted" )
      )
    }
  }
}
