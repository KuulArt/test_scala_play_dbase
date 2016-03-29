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

  def add(user: Equipment) = {
    uc.add(user)
  }

  def delete(id: Int): Future[Int] = {
    uc.delete(id)
  }

  def get(id: Int): Future[Option[Equipment]] = {
    uc.get(id)
  }

  def list: Future[Seq[Equipment]] = {
    uc.listAll
  }

  def count: Future[Int] = {
    uc.count
  }

  def update(id: Int, user: Equipment): Future[Int] = {
    uc.update(id, user)
  }
}
