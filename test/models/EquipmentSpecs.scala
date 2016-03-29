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
class EquipmentSpecs extends Specification {

  sequential

  def equipmentDAO(implicit app: Application) = {
    val app2EquipmentDAO = Application.instanceCache[EquipmentService]
    app2EquipmentDAO(app)
  }

  "App should " should {

    "add Equipment to database" in new WithApplication {

      val recordEntry = new Equipment(None, "Spot250", 20, 5, "Spot light")
      val newRecord = equipmentDAO.add(recordEntry)
      val t = Await.result(newRecord, Duration.Inf)
      println(t)
      t mustEqual "User successfully added"

      val listEquip = equipmentDAO.list
      val t2 = Await.result(listEquip, Duration.Inf)

    }

    "remove equipment from database" in new WithApplication {
      val entry = new Equipment(None, "Spot 250", 20, 5, "Spot")
      val databaseEntry = equipmentDAO.add(entry)
      val response = Await.result(databaseEntry, Duration.Inf)

      val deleteEntry = equipmentDAO.delete(1)
      val respone2 = Await.result(deleteEntry, Duration.Inf)

      val amountOfEntries = equipmentDAO.count
      val response3 = Await.result(amountOfEntries, Duration.Inf)
      response3 mustEqual 0
    }

    "list equipment in database" in new WithApplication {
      val entry = new Equipment(None, "Spot 250", 20, 5, "Spot")
      val f1 = equipmentDAO.add(entry)
      Await.result(f1, Duration.Inf)


      val future = equipmentDAO.list
      val users = Await.result(future, Duration.Inf)
      users.length mustEqual 1
      println("Equipment list: ", users.toList)
    }

    "retrieve a record from equipment database" in new WithApplication {
      val entry = new Equipment(None, "Spot 250", 20, 5, "Spot")
      val f1 = equipmentDAO.add(entry)
      Await.result(f1, Duration.Inf)

      val getEntry = equipmentDAO.get(1)
      val retrievedEntry = Await.result(getEntry, Duration.Inf)
      retrievedEntry.get.name mustEqual "Spot 250"
    }

    // "list users" in new WithApplication {
    //   val user = new UserClient(None, "Lohs", 2)
    //   val f1 = usersDAO.addUser(user)
    //   Await.result(f1, Duration.Inf)
    //
    //
    //   val future = usersDAO.listAllUsers
    //   val users = Await.result(future, Duration.Inf)
    //   users.length mustEqual 1
    //
    //   // println("users", users.toList)
    //
    // }
    //
    // "Modify user" in new WithApplication {
    //   val user = new UserClient(None, "Lohs", 2)
    //   val addUser = usersDAO.addUser(user)
    //   Await.result(addUser, Duration.Inf)
    //
    //   val user2 = new UserClient(None, "Lohs2", 4)
    //   val addUser2 = usersDAO.addUser(user2)
    //   Await.result(addUser2, Duration.Inf)
    //
    //   val user2future = usersDAO.getUser(2)
    //   val user2obj = Await.result(user2future, Duration.Inf).get
    //
    //   val user2modified = user2obj.copy(name = "Lohs112")
    //   println("user2modified:", user2modified)
    //
    //   val modifyUser = usersDAO.updateUser(user2modified.id.get, user2modified)
    //   val response = Await.result(modifyUser, Duration.Inf)
    //
    //   val future2 = usersDAO.getUser(2)
    //   val editedList = Await.result(future2, Duration.Inf).get
    //   editedList.name mustEqual "Lohs112"
    //
    //   println("second", editedList)
    //   val future = usersDAO.listAllUsers
    //   val users = Await.result(future, Duration.Inf)
    //   println("users", users.toList)
    //
    //
    // }
  }
}
