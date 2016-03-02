package controller
import play.api.test._
import play.api.test.Helpers._
import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._
/**
  * Created by kuulart on 16.2.3.
  */
//class ClientControlSpec extends PlaySpecification{
//
//  abstract class WithDbData extends WithApplication {
//    override def around[T: AsResult](t: => T): Result = super.around {
//      setupData()
//      t
//    }
//
//    def setupData() {
//      // setup data
//    }
//  }
//
//  "Computer model" should {
//
//    "be retrieved by id" in new WithDbData {
//      // your test code
//    }
//    "be retrieved by email" in new WithDbData {
//      // your test code
//    }
//  }



//}
