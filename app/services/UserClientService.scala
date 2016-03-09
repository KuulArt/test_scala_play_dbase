package services
import models._
import controllers._

import models.{UserClient, UsersClient}
import scala.concurrent.Future

object UserService {

  def addUser(user: UserClient): Future[String] = {
    UsersClient.add(user)
  }

  def deleteUser(id: Int): Future[Int] = {
    UsersClient.delete(id)
  }

  def getUser(id: Int): Future[Option[UserClient]] = {
    UsersClient.get(id)
  }

  def listAllUsers: Future[Seq[UserClient]] = {
    UsersClient.listAll
  }
}
