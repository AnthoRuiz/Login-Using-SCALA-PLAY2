package co.com.jakaboy.login.users.service

/**
 * Created by Usuario on 27/04/2016.
 */

import co.com.jakaboy.login.commons.service.{ OperationError, OperationFailed }
import co.com.jakaboy.login.users.controller.request._
import co.com.jakaboy.login.users.controller.response.UserAuthenticatedResponse
import co.com.jakaboy.login.users.infrastructure.{ UserDAO, UserRecord }
import co.com.jakaboy.login.users.service.domain._
import com.google.inject.{ Inject, Singleton }
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class UserService @Inject() ( dao: UserDAO ) {

  import co.com.jakaboy.login.commons.service.DomainOperation

  def getUser: Future[ Either[ DomainOperation, List[ UserDomain ] ] ] = {
    dao.getUser.map { users =>
      Right(
        users.map( user => UserDomain( user.id.get, user.name, user.email, user.pass, user.phone ) ).toList
      )
    }.recover {
      case e =>
        Logger.error( s"Error getting Users: ${e.getMessage}" ) //print on terminal any errors
        Left( OperationError( "error on Server" ) )
    }
  }

  def createUser( user: CreateUserRequest ): Future[ Either[ DomainOperation, UserDomainOperation ] ] = {
    dao.insertUser( UserRecord( None, user.name.toUpperCase, user.email.toUpperCase, user.pass, user.phone ) ).map { rowsAffected =>
      rowsAffected match {
        case 1 => Right( UserCreated( "created" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error inserting user: ${e.getMessage}" )
        Left( OperationError( "email already exists" ) )
    }
  }

  def updateUser( user: UpdateUserRequest ): Future[ Either[ DomainOperation, UserUpdated ] ] = {
    dao.updateUser( UserRecord(
      Some( user.id ), user.name.toUpperCase, user.email.toUpperCase, user.pass, user.phone
    ) ).map { result =>
      result match {
        case 1 => Right( UserUpdated( "updated" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error updating the User: ${e.getMessage}" )
        Left( OperationError( "error on Server" ) )
    }
  }

  def deleteUser( id: Int ): Future[ Either[ DomainOperation, UserDeleted ] ] = {
    dao.deleteUser( id ).map { result =>
      result match {
        case 1 => Right( UserDeleted( "deleted" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error remove user: ${e.getMessage}" )
        Left( OperationError( "error on Server" ) )
    }
  }

  def authenticateUser( user: AuthenticateUserRequest ): Future[ Either[ DomainOperation, UserAuthenticatedResponse ] ] = {
    dao.authenticateUser( user ).map { ( result: Option[ UserRecord ] ) =>
      result match {
        case Some( user ) => Right( UserAuthenticatedResponse( user.name, user.phone ) )
        case None         => Left( OperationFailed( "User does not exist" ) )
      }
    }
  }
}
