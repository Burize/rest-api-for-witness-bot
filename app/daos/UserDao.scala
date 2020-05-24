package daos

import javax.inject.Inject
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class UserDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends  HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def all(): Future[Seq[User]] = db.run(user.result)

  private class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Int]("id")

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def phone = column[String]("phone")

    override def * = (id, firstName, lastName, phone) <> (User.tupled, User.unapply)
  }

  private val user = TableQuery[UserTable]
}
