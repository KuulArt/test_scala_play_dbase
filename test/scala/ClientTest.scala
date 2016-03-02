package scala

import scalikejdbc._
import scalikejdbc.specs2.mutable.AutoRollback
import org.joda.time.DateTime
import org.specs2.mutable.Specification
/**
  * Created by kuulart on 16.2.3.
  */
  object ClientSpec extends Specification {

    sequential

    "Member should create a new record" in new AutoRollback {
      val before = client.count()
      client.create(3, "Chris")
      client.count() must_==(before + 1)
    }

    "Member should ... " in new AutoRollbackWithFixture {
      ...
    }

  }

  trait AutoRollbackWithFixture extends AutoRollback {
    // override def db = NamedDB('db2).toDB
    override def fixture(implicit session: DBSession) {
      sql"insert into members values (1, ${"Alice"}, ${DateTime.now})".update.apply()
      sql"insert into members values (2, ${"Bob"}, ${DateTime.now})".update.apply()
    }
  }

