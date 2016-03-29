package controllers

/**
  * Created by kuulart on 16.16.3.
  */

import models.{Equipment, EquipmentForm}
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import services.{EquipmentService}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc._
import play.api.libs.json._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import javax.inject.Inject

/**
  * Created by kuulart on 16.9.3.
  */


class EquipmentController @Inject()(equipmentService: EquipmentService, dbConfigProvider: DatabaseConfigProvider) extends Controller {

  //  def index: Action[AnyContent] = Action.async { implicit request =>
  //    UserService.listAllUsers map { users =>
  //      Ok(views.html.index(UserForm.form, users))
  //    }
  //  }
  implicit val clientWrites = new Writes[Equipment] {
    def writes(client: Equipment) = Json.obj(
      "id" -> client.id,
      "name" -> client.name,
      "price" -> client.price,
      "available" -> client.available,
      "description" -> client.description)
  }

  def add() = Action.async { implicit request =>
    EquipmentForm.form.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(Json.obj("errors" -> formWithErrors.errorsAsJson))),
      formData => {
        val newUser = Equipment(formData.id, formData.name, formData.price, formData.available, formData.description)
        equipmentService.add(newUser).map(res =>
          //          Redirect(routes.Application.index())
          Ok(Json.toJson(newUser))
        )
      }
    )
  }

  def list() = Action.async { implicit request =>
    equipmentService.list map { res =>
      println("equipmentList:", res)
      Ok(Json.toJson(res))
    }
  }

  def delete(id: Int) = Action.async { implicit request =>
    equipmentService.delete(id) map { res =>
      Redirect(routes.Application.index())
    }
  }

  def count() = Action.async { implicit request =>
    equipmentService.count map { res =>
      Ok(Json.toJson(res))
    }
  }

  def update(id: Int) = Action.async { implicit request =>
    EquipmentForm.form.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(Json.obj("errors" -> formWithErrors.errorsAsJson))),
      formData => {
        val newUser = new Equipment(Some(id), formData.name, formData.price, formData.available, formData.description)
        println("user:", newUser)
        equipmentService.update(id, newUser).map(res =>
          //Redirect(routes.Application.index())
          Ok(Json.toJson(newUser))
        )
      }
    )
  }
}
