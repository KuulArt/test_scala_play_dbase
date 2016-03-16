package services

/**
  * Created by kuulart on 16.16.3.
  */
import javax.inject.Inject

import models._
import controllers._

import models.{UserClient, UsersClient}
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future

class EquipmentService @Inject()(uc: EquipmentDAO, protected val dbConfigProvider: DatabaseConfigProvider) {

  def addUser(user: Equipment) = {
    uc.add(user)
  }

  def deleteUser(id: Int): Future[Int] = {
    uc.delete(id)
  }

  def getUser(id: Int): Future[Option[Equipment]] = {
    uc.get(id)
  }

  def listAllUsers: Future[Seq[Equipment]] = {
    uc.listAll
  }

  def count: Future[Int] = {
    uc.count
  }

  def updateUser(id: Int, user: Equipment): Future[Int] = {
    uc.update(id, user)
  }
}