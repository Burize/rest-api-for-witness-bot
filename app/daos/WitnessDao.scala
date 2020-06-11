package daos

import javax.inject.{Inject, Singleton}
import models.Witness
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class WitnessDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends  HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def all(): Future[Seq[Witness]] = db.run(witnesses.result)

  def create(witness: Witness): Future[Int] = {
    db.run(witnesses insertOrUpdate  witness )
  }

  def update(witness: Witness): Future[Int] = {
    val selectQ = witnesses.filter(_.id === witness.id)

    db.run(selectQ.result.head.flatMap { data =>
      selectQ.update(data.partialCopy(witness))
    })
  }

  private class WitnessTable(tag: Tag) extends Table[Witness](tag, "witness") {
    def id = column[Int]("id", O.PrimaryKey)

    def firstName = column[Option[String]]("first_name")

    def lastName = column[Option[String]]("last_name")

    def phone = column[Option[String]]("phone")

    override def * = (id, firstName, lastName, phone) <> (Witness.tupled, Witness.unapply)
  }

  private val witnesses = TableQuery[WitnessTable]
}
