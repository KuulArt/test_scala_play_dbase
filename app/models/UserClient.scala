package models
import play.api.data._
import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
//import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
//import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
//import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.{Singleton, Inject}
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

/**
  * Created by kuulart on 16.9.3.
  */
case class UserClient(id: Option[Int], name: String, discount: Int)

//case class UserFormData(name: String, discount: Int)

object UserForm {

  val form = Form(
    mapping(
      "id" -> ignored[Option[Int]](None),
      "name" -> nonEmptyText,
      "discount" -> number.verifying(min(0), max(100))
    )(UserClient.apply)(UserClient.unapply)
  )
}

class UserTableDef(tag: Tag) extends Table[UserClient](tag, "clients") {

  def id = column[Option[Int]]("id", O.PrimaryKey,O.AutoInc)
  def name = column[String]("name")
  def discount = column[Int]("discount")

  override def * =
    (id, name, discount) <>(UserClient.tupled, UserClient.unapply)
}

@Singleton()
class UsersClient @Inject()(dbConfigProvider: DatabaseConfigProvider) {

//  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  var users = TableQuery[UserTableDef]

  def add(user: UserClient): Future[String] = {
    dbConfig.db.run(users += user).map(res => "User successfully added").recover {
      case ex: Exception =>
        println(ex)
        ex.getCause.getMessage
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