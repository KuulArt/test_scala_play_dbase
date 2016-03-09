package controllers

import models.clients
import play.api.test._
import scalikejdbc.DBSession
import scalikejdbc.specs2.mutable.AutoRollback
import scalikejdbc._

class ClientControllerSpec extends PlaySpecification with settings.DBSettings {

  trait AutoRollbackWithFixture extends AutoRollback {

    override def db = NamedDB('test).toDB

    override def fixture(implicit session: DBSession) {

      println("fixture session", session)

      // create clients table and insert test data
      sql"""
        CREATE TABLE clients (
          ID INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
          name VARCHAR(255) NOT NULL,
          discount TINYINT(3) UNSIGNED,
          PRIMARY KEY (ID)
        );
      """.execute.apply()
//      sql"""INSERT INTO clients (name, discount) VALUES ('John', 20)""".execute.apply()

      clients.create("test", 20)

      println("created clients table")
    }
  }

  val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inMemoryDatabase("test"))
  "run an application" in new WithApplication(appWithMemoryDatabase) {

    val controller = new clientControl()
    println(controller.session)

    // create fake request to index action
    val result = controller.showAll()(FakeRequest())
  }

  "respond to the index Action" in new AutoRollbackWithFixture {
//  "respond to the index Action" in {

//    val localSession = NamedAutoSession('test)
    val controller = new clientControl()
    println(controller.session)
    println(session)

    // create fake request to index action
    val result = controller.showAll()(FakeRequest())

    // test result from index action
    status(result) must equalTo(OK)
    contentType(result) must beSome("application/json")
    contentAsString(result) must contain("Get Database!")
  }
}