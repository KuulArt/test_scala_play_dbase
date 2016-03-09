package models
import play.api.data._
import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuulart on 16.9.3.
  */
case class UserClient(id: Int, name: String, discount: Int)

case class UserFormData(name: String, discount: Int)

object UserForm {

  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "disocunt" -> number.verifying(min(0), max(100))
    )(UserFormData.apply)(UserFormData.unapply)
  )
}

class UserTableDef(tag: Tag) extends Table[UserClient](tag, "user") {

  def id = column[Int]("id", O.PrimaryKey,O.AutoInc)
  def name = column[String]("name")
  def discount = column[Int]("discount")

  override def * =
    (id, name, discount) <>(UserClient.tupled, UserClient.unapply)
}

object UsersClient {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  var users = TableQuery[UserTableDef]

  def add(user: UserClient): Future[String] = {
    dbConfig.db.run(users += user).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(users.filter(_.id === id).delete)
  }

  def get(id: Int): Future[Option[UserClient]] = {
    dbConfig.db.run(users.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[UserClient]] = {
    dbConfig.db.run(users.result)
  }

}