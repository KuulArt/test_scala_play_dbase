package models
import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._



/**
  * Created by kuulart on 16.9.3.
  */
class UserClientSpecs extends Specification{

  val appWithMemoryDatabase = FakeApplication(additionalConfiguration = inMemoryDatabase("testdb"))

  step("start application")

  "App should " should {
    "run an application" in new WithApplication(appWithMemoryDatabase) {

    }

  }
}
