package controllers
import models.{UserClient, UsersClient, UserForm}
import play.api.data._
import play.api.data.validation.Constraints._
import scala.concurrent.Future
import services.UserService
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc._
import play.api.libs.json._
import services.UserService

/**
  * Created by kuulart on 16.9.3.
  */


class UserClientControl extends Controller {

//  def index: Action[AnyContent] = Action.async { implicit request =>
//    UserService.listAllUsers map { users =>
//      Ok(views.html.index(UserForm.form, users))
//    }
//  }
  implicit val clientWrites = new Writes[UserClient] {
    def writes(client: UserClient) = Json.obj(
      "name" -> client.name,
      "discount" -> client.discount)
  }

  def addUser() = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      formWithErrors => Future.successful(Ok("Fail")),
      formData => {
        val newUser = UserClient(0, formData.name, formData.discount)
        UserService.addUser(newUser).map(res =>
          Redirect(routes.Application.index())
        )
      }
    )
  }

  def listClients() = Action.async { implicit request =>
    UserService.listAllUsers map {res =>
      println("clientsList:", res)
      Ok(Json.toJson(res))
    }
    }

  def deleteUser(id: Int) = Action.async { implicit request =>
    UserService.deleteUser(id) map { res =>
      Redirect(routes.Application.index())
    }
  }

}