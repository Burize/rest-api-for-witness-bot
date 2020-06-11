package controllers

import daos.ReportDao
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.{Inject, Singleton}
import play.api.libs.circe.Circe
import play.api.mvc._
import shared._
import slogging.{LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.concurrent.ExecutionContext.Implicits.global

case class ReportRequest(userId: Int, message: String)
@Singleton
class ReportController @Inject()(cc: ControllerComponents, reportDao: ReportDao)  extends AbstractController(cc) with Circe with ServerError{

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

def all() = Action.async{ _ =>
 for (
    reports <- reportDao.all()
  ) yield Ok(reports.asJson)

}
  def create() = Action.async(circe.json[ReportRequest]){ req =>
    reportDao.create(req.body.userId, req.body.message ).map(_ => Ok).recover(recoverError)
  }

  def getById(id: Int) = Action.async{ req =>
    reportDao.getById(id).map( report => Ok(report.asJson)).recover(recoverError)
  }

  def getUsersReport(userId: Int) = Action.async{ req =>
    reportDao.getByUserId(userId).map(reports => Ok(reports.asJson)).recover(recoverError)
  }

}
