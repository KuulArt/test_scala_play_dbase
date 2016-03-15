package services

import javax.inject.Inject

import models._
import controllers._

import models.{UserClient, UsersClient}
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future

class UserService @Inject()(uc: UsersClient, protected val dbConfigProvider: DatabaseConfigProvider) {

  def addUser(user: UserClient) = {
    uc.add(user)
  }

  def deleteUser(id: Int): Future[Int] = {
    uc.delete(id)
  }

  def getUser(id: Int): Future[Option[UserClient]] = {
    uc.get(id)
  }

  def listAllUsers: Future[Seq[UserClient]] = {
    uc.listAll
  }

  def updateUser(id: Int, user: UserClient): Future[Int] = {
    uc.update(id, user)
  }
}
