package controllers

import daos.WitnessDao
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import models.Witness
import play.api.libs.circe.Circe
import play.api.mvc._
import shared._
import slogging.{LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global


case class CreateUserRequest(userId: String)

case class UserID(userId: Int)


class WitnessController @Inject()(cc: ControllerComponents, witnessDao: WitnessDao)  extends AbstractController(cc) with Circe with ServerError{

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

def all():  Action[AnyContent] = Action.async{ _ =>
 for (
    users <- witnessDao.all()
  ) yield Ok(users.asJson)

}
  def create() = Action.async(circe.json[Witness]){ req =>
    witnessDao.create(req.body).map( _ => Ok).recover(recoverError)
  }

  def update() = Action.async(circe.json[Witness]){ req =>
    witnessDao.update(req.body).map( _ => Ok)
  }
}
