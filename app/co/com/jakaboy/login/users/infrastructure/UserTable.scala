package co.com.jakaboy.login.users.infrastructure

import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
 * Created by Usuario on 27/04/2016.
 */

final case class UserRecord( id: Option[ Int ], name: String, email: String, pass: String, phone: String )

final class UserTable( tag: Tag ) extends Table[ UserRecord ]( tag, "users" ) {

  def id = column[ Option[ Int ] ]( "id", O.PrimaryKey, O.AutoInc )

  def name = column[ String ]( "name" )

  def email = column[ String ]( "email" )

  def pass = column[ String ]( "pass" )

  def phone = column[ String ]( "phone" )

  override def * : ProvenShape[ UserRecord ] = ( id, name, email, pass, phone ) <> ( UserRecord.tupled, UserRecord.unapply )
}
