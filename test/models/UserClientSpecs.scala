package models

import play.api.Application
import play.api.test._
import org.specs2.mutable._
import services._
import scala.concurrent.duration.Duration
import scala.concurrent.Await

/**
  * Created by kuulart on 16.9.3.
  */
class UserClientSpecs extends Specification {

  sequential

  def usersDAO(implicit app: Application) = {
    val app2UsersDAO = Application.instanceCache[UserService]
    app2UsersDAO(app)
  }

  "App should " should {

    "add value to database" in new WithApplication {

      val recordEntry = new UserClient(None, "Lohs", 2)
      val newRecord = usersDAO.addUser(recordEntry)
      val t = Await.result(newRecord, Duration.Inf)

      t mustEqual "User successfully added"
    }

    "list users" in new WithApplication {
      val user = new UserClient(None, "Lohs", 2)
      val f1 = usersDAO.addUser(user)
      Await.result(f1, Duration.Inf)


      val future = usersDAO.listAllUsers
      val users = Await.result(future, Duration.Inf)
      users.length mustEqual 1

      // println("users", users.toList)

    }

    "Modify user" in new WithApplication {
      val user = new UserClient(None, "Lohs", 2)
      val addUser = usersDAO.addUser(user)
      Await.result(addUser, Duration.Inf)
      val user2 = new UserClient(None, "Lohs2", 4)
      val addUser2 = usersDAO.addUser(user2)
      Await.result(addUser2, Duration.Inf)

      val modifyUser = usersDAO.updateUser(2, new UserClient(None, "NavLohs", 10))
      val response = Await.result(modifyUser, Duration.Inf)
      val future2 = usersDAO.getUser(1)
      val editedList = Await.result(future2, Duration.Inf)
//      editedList.name mustEqual "NavLohs"
      println("second", editedList.get)
      val future = usersDAO.listAllUsers
      val users = Await.result(future, Duration.Inf)
      println("users", users.toList)

    }


    //    "delete a record" in new WithApplication {
    ////      println("before second test")
    ////      val recordEntry = new UserClient(Some(0), "Lielaks Lohs", 5)
    ////      val newRecord = UserService.addUser(recordEntry)
    ////      newRecord.map {
    ////        v => println("newRecord value", v)
    ////      }
    ////      newRecord.onComplete {
    ////        case Success(value) => println(s"Got the callback, meaning = $value")
    ////        case Failure(e) => println(e.getMessage)
    ////      }
    ////      val recordEntry2 = new UserClient(Some(1), "Lielaks Lohs2", 50)
    ////      val newRecord2 = UserService.addUser(recordEntry2)
    ////      val countOne = UserService.listAllUsers.map {
    ////        res =>
    ////          println(res.length)
    ////      }
    ////      val deleteUser = UserService.deleteUser(1)
    ////      val countTwo = UserService.listAllUsers.map {
    ////        res =>
    ////          res should_==(1)
    ////          res should !==(2)
    ////      }
    //    }


  }
}
