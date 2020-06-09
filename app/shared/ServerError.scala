package shared
import play.api.mvc._

trait ServerError extends AbstractController {
  val recoverError: PartialFunction[Throwable, Result] = {
    case _ =>
      InternalServerError("Cannot write in the database")
  }

}
