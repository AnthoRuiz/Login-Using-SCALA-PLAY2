package co.com.jakaboy.login.commons.service

trait DomainOperation

case class OperationFailed( cause: String ) extends DomainOperation

case class OperationError( cause: String ) extends DomainOperation

case class OperationSuccess( cause: String ) extends DomainOperation

