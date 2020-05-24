package controllers

import javax.inject._
import play.api.libs.circe.Circe
import play.api.mvc._

case class RegistrationPayload(firstName: String, lastName: String, phone: String, asdasdasd: String)

@Singleton
class AuthController @Inject()(val controllerComponents: ControllerComponents) extends BaseController  with Circe{

  def auth() = ???

}
