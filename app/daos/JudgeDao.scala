package daos

import javax.inject.Inject
import models.Judge
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class JudgeDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends  HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def all(): Future[Seq[Judge]] = db.run(judge.result)

  private class JudgeTable(tag: Tag) extends Table[Judge](tag, "judge") {
    def id = column[Int]("id")

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    override def * = (id, firstName, lastName) <> (Judge.tupled, Judge.unapply)
  }

  private val judge = TableQuery[JudgeTable]
}
