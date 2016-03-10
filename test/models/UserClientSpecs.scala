package models

import org.h2.engine.Database
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
import models._
import scala.concurrent.duration.Duration

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by kuulart on 16.9.3.
  */
class UserClientSpecs extends Specification{

  val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inMemoryDatabase("testdb"))

  //val dbConfig = DatabaseConfigProvider.get[JdbcProfile]("testdb")(Play.current)

  step("start application")

  "App should " should {
    "add value to database" in new WithApplication(appWithMemoryDatabase){

        val recordEntry = new UserClient(0, "Lohs", 2)
        val newRecord = UserService.addUser(recordEntry)
        println("before")
          newRecord.onComplete {
            case Success(value) => println(s"Got the callback, meaning = $value")
            case Failure(e) => println(e.getMessage)
          }
        newRecord should not beNull

      //        val result = Await.result(newRecord, new Duration(1))

//          map {  newRec =>
//          println(newRec)
//        }
    }


  }
}
