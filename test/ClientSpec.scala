
import scalikejdbc._
import scalikejdbc.specs2.mutable.AutoRollback
//import org.joda.time.DateTime
import org.specs2.mutable.Specification
import models.clients

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
/**
  * Created by kuulart on 16.2.3.
  */

@RunWith(classOf[JUnitRunner])
class ClientSpec extends Specification {

  sequential

  "Member should create a new record" in new AutoRollbackWithFixture {
    //      val before = clients.count()
    //      clients.create("pro-sound" , 20)
    "Asdfsdfs" must_==("fff")
    //      clients.count() must_==(before + 1)
  }

  //    "Member should ... " in new AutoRollbackWithFixture {
  //      ...
  //    }

}

trait AutoRollbackWithFixture extends AutoRollback {
   override def db = NamedDB('testdb).toDB
  override def fixture(implicit session: DBSession) {
    sql"insert into members values (1, ${"Alice"}, ${49})".update.apply()
    sql"insert into members values (2, ${"Bob"}, ${20})".update.apply()
  }
}

