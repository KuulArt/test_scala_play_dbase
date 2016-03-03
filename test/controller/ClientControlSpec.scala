package controller

import controllers._
import models._
import org.specs2.execute._
import play.api.test._
import play.api.test.Helpers._
import org.joda.time._
import scalikejdbc._
import scalikejdbc.config._
import play.api.libs.json._
import org.specs2.specification._
import org.specs2.mutable._
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.specs2.mutable._
import play.api.test.WithApplication
import play.api.db.{Database, Databases}
import play.api.db.evolutions._


/**
  * Created by kuulart on 16.2.3.
  */
//trait DatabaseContext extends AroundEach with settings.DBSettings{
//  // you need to define the "around" method
//  def around[R: AsResult](r: => R): Result = {
//    //def db = NamedDB('testdb).toDB
//    openDatabaseTransaction
//    try AsResult(r)
//    finally closeDatabaseTransaction
//  }
//  // do what you need to do with the database
//  def openDatabaseTransaction = withMyDatabase { testdb =>
//    //NamedDB('testdb)
//    settings.DBSettings.initialize()
//    val connection = testdb.getConnection()
//    Evolutions.applyEvolutions(testdb)
//  }
//  def closeDatabaseTransaction = withMyDatabase { testdb =>
//    val connection = testdb.shutdown()
//    Evolutions.cleanupEvolutions(testdb)
//  }
//  def withMyDatabase[T](block: Database => T) = {
//    Databases.inMemory(
//      name = "testdb",
//      urlOptions = Map(
//        "MODE" -> "MYSQL"
//      ),
//      config = Map(
//        "logStatements" -> true
//      )
//    )
//  }
//}



class ClientControlSpec extends PlaySpecification with settings.DBSettings {
  NamedDB('testdb)
  def inSpecMemoryDatabase(name: String = "testdb", options: Map[String, String] = Map.empty[String, String]): Map[String, String] = {
    val optionsForDbUrl = options.map { case (k, v) => k + "=" + v }.mkString(";", ";", "")
    NamedDB('testdb)
    Map(
      ("db." + name + ".driver") -> "org.h2.Driver",
      ("db." + name + ".url") -> ("jdbc:h2:mem:play-test-" + scala.util.Random.nextInt + optionsForDbUrl)
    )
  }

  val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inSpecMemoryDatabase())


  "Client Controller" should {
    "respond to the index Action" in new WithApplication(appWithMemoryDatabase) {
        val json = new controllers.clientControl().showAll()(FakeRequest())
        println(Json.parse(contentAsString(json)))
        status(json) must equalTo(OK)

        //contentType(result) must beSome("application/xml")
        //contentAsString(result) must contain("products")
    }
  }



}
