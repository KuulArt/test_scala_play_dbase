package models

import akka.actor.Scope
import org.h2.engine.{Session, Database}
import org.specs2.execute
import org.specs2.execute.AsResult
import play.api.db.slick._
import play.api.Play
import play.api.test._
import play.api.Play.current
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import slick.driver.JdbcProfile
import services._

import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import models._
import scala.concurrent.duration.Duration

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by kuulart on 16.9.3.
  */
class UserClientSpecs extends Specification{

  //val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inMemoryDatabase("testdb"))

  //val dbConfig = DatabaseConfigProvider.get[JdbcProfile]("testdb")(Play.current)
  abstract class WithDbData extends WithApplication {
    override def around[T: AsResult](t: => T): execute.Result = super.around {
      setupData()
      t
    }

    def setupData() {
      val dbEntries = UsersClient.users
      val action = dbEntries.delete
    }
  }
  sequential



  "App should " should {
    step("Add Record")
    "add value to database" in new WithApplication {
      println("before first test")
      val recordEntry = new UserClient(None, "Lohs_atkal", 2)
      val newRecord = UserService.addUser(recordEntry)
      newRecord.onComplete {
        case Success(value) => println(s"Got the callback, meaning = $value")
        case Failure(e) => println(e.getMessage)
      }
      newRecord should not beNull
      val count = UserService.listAllUsers.map {
        v =>
          println("the number of database entries are " + v.length)
      }
    }

    step("Delete record")

    "delete a record" in new WithApplication {
      println("before second test")
      val recordEntry = new UserClient(Some(0), "Lielaks Lohs", 5)
      val newRecord = UserService.addUser(recordEntry)
      newRecord.map {
        v => println("newRecord value", v)
      }
      newRecord.onComplete {
        case Success(value) => println(s"Got the callback, meaning = $value")
        case Failure(e) => println(e.getMessage)
      }
      val recordEntry2 = new UserClient(Some(1), "Lielaks Lohs2", 50)
      val newRecord2 = UserService.addUser(recordEntry2)
      val countOne = UserService.listAllUsers.map {
        res =>
          println(res.length)
      }
      val deleteUser = UserService.deleteUser(1)
      val countTwo = UserService.listAllUsers.map {
        res =>
          res should_==(1)
          res should !==(2)
      }
    }


  }
}
