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
class ClientControlSpec extends Specification {

//  abstract class WithDbData extends WithApplication {
//    override def around[T: AsResult](t: => T): Result = super.around {
//      setupData()
//      t
//    }
//
//    def setupData() {
//      DBs.setup('testdb);
//      def openDatabaseTransaction() {
//        def db = NamedDB('testdb).toDB
//      }
//    }
//  }

  def withMyDatabase[T](block: Database => T) = {
    Databases.withInMemory(
      name = "testdb",
      urlOptions = Map(
        "MODE" -> "MYSQL"
      ),
      config = Map(
        "logStatements" -> true
      )
    ) { testdb =>

      Evolutions.withEvolutions(testdb) {
        block(testdb)
      }
    }
  }



  "Client Controller" should {
    "respond to the index Action" in withMyDatabase { testdb=>
      val connection = testdb.getConnection()
        val json = new controllers.clientControl().showAll()(FakeRequest())
        println(Json.parse(contentAsString(json)))
        status(json) must equalTo(OK)

        //contentType(result) must beSome("application/xml")
        //contentAsString(result) must contain("products")
    }
    "be retrieved by email" in  new WithApplication{
      // your test code
    }
  }



}
