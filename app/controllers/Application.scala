package controllers

import play.api._
import play.api.mvc._


class Application extends Controller {

  def index() = Action {
//    implicit val session = AutoSession

    // for now, retrieves all data as Map value
//    val entities: List[Map[String, Any]] =
//      sql"select * from clients".map(_.toMap).list.apply()

//    println(entities)

    Ok(views.html.index("You are ready."))
  }

}
