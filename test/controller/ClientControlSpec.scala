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

/**
  * Created by kuulart on 16.2.3.
  */
class ClientControlSpec extends Specification {

  abstract class WithDbData extends WithApplication {
    override def around[T: AsResult](t: => T): Result = super.around {
      setupData()
      t
    }

    def setupData() {
      DBs.setup('testdb);
      def openDatabaseTransaction() {

        def db = NamedDB('testdb).toDB
        def fixture(implicit session: DBSession) {
          val user1 = clients.create("user1", 20)
          val user2 = clients.create("user2", 30)
          val user3 = clients.create("user3", 25)
        }
      }
    }
  }


  "Client Controller" should {
    "respond to the index Action" in new WithDbData{
      val json = new controllers.clientControl().showAll()(FakeRequest())
      println(contentAsString(json))
      status(json) must equalTo(OK)

      //contentType(result) must beSome("application/xml")
      //contentAsString(result) must contain("products")
    }
    "be retrieved by email" in  new WithApplication{
      // your test code
    }
  }



}
