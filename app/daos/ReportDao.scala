package daos

import javax.inject.Inject
import models.{Report}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class ReportDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends  HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def all(): Future[Seq[Report]] = db.run(reports.result)

  def create(userId: Int, message: String) = db.run(reports += Report(0, userId, message))

  def getById(id: Int) = db.run(reports.filter(_.id === id).result.headOption)

  def getByUserId(userId: Int) = db.run(reports.filter(_.userId === userId).result)

  protected class ReportTable(tag: Tag) extends Table[Report](tag, "reports") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def userId = column[Int]("userId")

    def message = column[String]("message")

    override def * = (id, userId, message) <> (Report.tupled, Report.unapply)
  }

  protected val reports = TableQuery[ReportTable]
}
