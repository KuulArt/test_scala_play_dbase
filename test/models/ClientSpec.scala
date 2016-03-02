package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._
import scalikejdbc.config._
/**
  * Created by kuulart on 16.2.3.
  *
  * with settings.DBSettings
  */
class ClientSpec extends Specification {

  DBs.setup('testdb);

  val _p = clients.c

    trait AutoRollbackWithFixture extends AutoRollback {
      override def db = NamedDB('testdb).toDB
      override def fixture(implicit session: DBSession) {

        val user1 = clients.create("user1", 20)
        val user2 = clients.create("user2", 30)
        val user3 = clients.create("user3", 25)

      }
    }

  "Member should create a new record" in new AutoRollbackWithFixture {
    //      val before = clients.count()
    //      clients.create("pro-sound" , 20)
    //"Asdfsdfs" must_==("fff")
    //println(clients.findAll())
    //      clients.count() must_==(before + 1)
  }

//    "clients" should {
//      "find with skills" in new AutoRollbackWithFixture {
//        val seratch = Programmer.findAllBy(sqls.eq(_p.name, "seratch")).head
//        seratch.skills.size should_== (3)
//      }
//      "find no skill programmers" in new AutoRollbackWithFixture {
//        val noSkillProgrammers = Programmer.findNoSkillProgrammers()
//        noSkillProgrammers.size should_== (1)
//      }
//      "find by primary keys" in new AutoRollbackWithFixture {
//        val id = Programmer.findAll().head.id
//        val maybeFound = Programmer.find(id)
//        maybeFound.isDefined should beTrue
//      }
//      "find all records" in new AutoRollbackWithFixture {
//        val allResults = Programmer.findAll()
//        allResults.size should_== (3)
//      }
//      "count all records" in new AutoRollbackWithFixture {
//        val count = Programmer.countAll()
//        count should_== (3L)
//      }
//      "find by where clauses" in new AutoRollbackWithFixture {
//        val results = Programmer.findAllBy(sqls.isNotNull(_p.companyId))
//        results.head.name should_== ("seratch")
//      }
//      "count by where clauses" in new AutoRollbackWithFixture {
//        val count = Programmer.countBy(sqls.isNull(_p.companyId))
//        count should_== (2L)
//      }
      "create new record" in new AutoRollbackWithFixture {
        val martin = clients.create("Martin", 23)
        martin.ID should not beNull;
        println(clients.findAll())
      }
      "save a record" in new AutoRollbackWithFixture {
        val entity = clients.find(1)
        entity.isDefined must_== true
        entity.map {
          client =>
              val new_client = client.copy(name = "NEW")
              clients.save(new_client)
              println(clients.findAll())
            // ...
        }

        val entity2 = clients.find(1)
        entity2.isDefined must_== true
        entity2.map {
          client =>
            client.name must_==("NEW")
            println("success")
        }

        //        entity.copy(name = "Bob").save()
//        val updated = Programmer.find(entity.id).get
//        updated.name should_== ("Bob")
      }

      "findAll test by length" in new AutoRollbackWithFixture {
        val before = clients.findAll().length
        clients.create("big", 22)
        clients.findAll().length must_== (before+1)
      }

      "find test" in new AutoRollbackWithFixture {
        val test_clients = new client(1, "prosound", 20)
        clients.find(1) must_==(Some(test_clients))
      }
//      "destroy a record" in new AutoRollbackWithFixture {
//        val entity = Programmer.findAll().head
//        entity.destroy()
//        val shouldBeNone = Programmer.find(entity.id)
//        shouldBeNone.isDefined should beFalse
//        Programmer.countAll should_== (2L)
//      }
//    }

}

/**
  *
  * import scalikejdbc._
  * import scalikejdbc.specs2.mutable.AutoRollback
  * //import org.joda.time.DateTime
  * import org.specs2.mutable.Specification
  * import models.clients

  * import org.specs2.mutable._
  * import org.specs2.runner._
  * import org.junit.runner._
  * /**
  * Created by kuulart on 16.2.3.
  */

  * @RunWith(classOf[JUnitRunner])
  * class ClientSpec extends Specification {

  * sequential

  * "Member should create a new record" in new AutoRollbackWithFixture {
  * //      val before = clients.count()
  * //      clients.create("pro-sound" , 20)
  * "Asdfsdfs" must_==("fff")
  * //      clients.count() must_==(before + 1)
  * }

  * //    "Member should ... " in new AutoRollbackWithFixture {
  * //      ...
  * //    }

  * }

  * trait AutoRollbackWithFixture extends AutoRollback {
  * override def db = NamedDB('testdb).toDB
  * override def fixture(implicit session: DBSession) {
  * sql"insert into members values (1, 'Alice', 49)".update.apply()
  * sql"insert into members values (2, 'Bob', 20)".update.apply()
  * }
  * }
  */
