package co.com.jakaboy.login.users.service.domain

/**
 * Created by Usuario on 27/04/2016.
 */
final case class UserDomain( id: Int, name: String, email: String, pass: String, phone: String )

trait UserDomainOperation

case class UserCreated( s: String ) extends UserDomainOperation

case class UserUpdated( s: String ) extends UserDomainOperation

case class UserDeleted( s: String ) extends UserDomainOperation