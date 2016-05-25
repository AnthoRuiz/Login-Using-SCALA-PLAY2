package co.com.jakaboy.login.users.infrastructure

import co.com.jakaboy.login.users.controller.request.AuthenticateUserRequest
import com.google.inject.Inject
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
 * Created by Usuario on 27/04/2016.
 */

final class UserDAO @Inject() ( @NamedDatabase( "default" ) protected val dbConfigProvider:DatabaseConfigProvider )
    extends HasDatabaseConfigProvider[ JdbcProfile ] {

  import driver.api._

  private val users = TableQuery[ UserTable ]

  def getUser: Future[ Seq[ UserRecord ] ] = {
    val query = users.map( x => x )
    db.run( query.result )
  }

  def insertUser( user: UserRecord ): Future[ Int ] = {
    val insert = users += user
    db.run( insert )
  }

  def updateUser( user: UserRecord ): Future[ Int ] = {
    val update = users.filter( _.id === user.id ).update( user )
    db.run( update )
  }

  def deleteUser( id: Int ): Future[ Int ] = {
    val delete = users.filter( _.id === id ).delete
    db.run( delete )
  }

  def authenticateUser( user: AuthenticateUserRequest ): Future[ Option[ UserRecord ] ] = {
    val autentica = users.filter( x => x.email === user.email.toUpperCase && x.pass === user.pass )
    db.run( autentica.result.headOption )
  }

}