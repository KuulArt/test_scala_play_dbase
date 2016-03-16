package models

import javax.management.Descriptor

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
  * Created by kuulart on 16.16.3.
  */
case class Equipment(id: Option[Int], name: String, price: Int, available: Int, description: String)

object EquipmentForm {

  val form = Form(
    mapping(
      "id" -> ignored[Option[Int]](None),
      "name" -> nonEmptyText,
      "price" -> number.verifying(min(0)),
      "available" -> number,
      "description" -> ignored(String)
    )(Equipment.apply)(Equipment.unapply)
  )
}

class EquipmentTableDef(tag: Tag) extends Table[Equipment](tag, "equipment") {

  def id = column[Option[Int]]("id", O.PrimaryKey,O.AutoInc)
  def name = column[String]("name")
  def price = column[Int]("price")
  def available = column[Int]("available")
  def description = column[String]("description")

  override def * =
    (id, name, price, available, description) <>(Equipment.tupled, Equipment.unapply)
}

@Singleton()
class EquipmentDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) {

  //  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  var users = TableQuery[EquipmentTableDef]

  def add(user: Equipment): Future[String] = {
    dbConfig.db.run(users += user).map(res => "User successfully added").recover {
      case ex: Exception =>
        println(ex)
        ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(users.filter(_.id === id).delete)
  }

  def get(id: Int): Future[Option[Equipment]] = {
    dbConfig.db.run(users.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Equipment]] = {
    dbConfig.db.run(users.result)
  }

  def count: Future[Int] = {
    dbConfig.db.run(users.length.result)
  }

  def update(id: Int, client: Equipment): Future[Int] = {
    dbConfig.db.run(users.filter(_.id === id).update(client))
    //    dbConfig.db.run(users.filter(_.id === id).map(_.discount).update(client.discount))
  }

}