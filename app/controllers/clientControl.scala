package controllers


//import com.github.tototoshi.play2.json4s.native._
import models._
//import org.json4s._
//import org.json4s.ext.JodaTimeSerializers
import play.api.data.Forms._
import play.api.data._
import play.api.data.validation.Constraints._
import play.api.mvc._
import play.api.libs.json._

/**
  * Created by kuulart on 16.24.2.
  */
class clientControl extends Controller{


  implicit val clientWrites = new Writes[client] {
    def writes(client: client) = Json.obj(
      "name" -> client.name,
      "discount" -> client.discount)
  }

  case class clientData(name: String, discount: Int)

  val userForm = Form(
    mapping(
      "name" -> text.verifying(nonEmpty),
      "discount" -> number.verifying(min(0), max(100))
    )(clientData.apply)(clientData.unapply)
  )


  /*
   * 
   */
  def show(ID: Long) = Action {
    clients.find(ID) match {
      case Some(client) => Ok(Json.toJson(client))
      case _ => NotFound
    }
  }

  def showAll = Action {
    val clientsList = clients.findAll()
    Ok(Json.toJson(clientsList))
  }


    def addValue = Action { implicit request =>
      userForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest("fail")
        },
        formData => {
          clients.create(formData.name, formData.discount)
          Ok("success")
        }
      )
    }
}
