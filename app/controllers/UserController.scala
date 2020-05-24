package controllers

import daos.UserDao
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.libs.circe.Circe
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

class UserController @Inject()(cc: ControllerComponents, userDao: UserDao)  extends AbstractController(cc) with Circe{
def all():  Action[AnyContent] = Action.async{ _ =>
 for (
    users <- userDao.all()
  ) yield Ok(users.asJson)

}
}
