package models

import play.api.mvc.Session
import scalikejdbc._
import org.joda.time.DateTime
import SQLInterpolation._

/**
  * Created by kuulart on 16.24.2.
  * Shis ir modulis ar metodem, kas tiks izsauktas no controlieriem.
  * Sheit notiek galvenaa darbiiba un sazinja ar databaazi
  *
  * Sakumaa uztaisam klasi, kas savaa zinjaa ir ka savs datatips, kuru parvietos apkaart un izmantos
  * databaazhu menedzeeshanaa
  */
case class client(
    ID: Long,
    name: String,
    discount: Int
) {
/**
  * Creating a method that calls object clients method save which saves the database with corresponding
  * data
  */
  def save()(implicit session: DBSession = AutoSession): client = clients.save(this)(session)
  //def destroy()(implicit session: DBSession = clients.AutoSession): Unit = clients.destroy(ID)(session)
}

/**
  * SQLSyntaxSupport is a module with SQL management methods
  * Creating an object clients that contains all the methods for database manipulation
  */
object clients extends SQLSyntaxSupport[client] {
  /**
    *This method usually is already provided but for some reason in the example
    * I followed it was overriden with the following one
    * @param c
    * @param rs
    * @return
    */
  def apply(c: SyntaxProvider[client])(rs: WrappedResultSet): client = apply(c.resultName)(rs)
  def apply(c: ResultName[client])(rs: WrappedResultSet): client = new client(
    ID = rs.get(c.ID),
    name = rs.get(c.name),
    discount = rs.get(c.discount)
  )

  /**
    *
    */
  val c = clients.syntax("c")
//  private val isNotDeleted = sqls.isNull(c.deletedAt)
  /**
    *
     * @param ID
    * @param session
    * @return
    */
  def find(ID: Long)(implicit session: DBSession = autoSession): Option[client] = withSQL {
  select.from(clients as c).where.eq(c.ID, ID)
  }.map(clients(c)).single.apply()

  /**
    *
    * @param session
    * @return
    */
  def findAll()(implicit session: DBSession): List[client] = withSQL {
    println("findAll session:", session)
    select.from(clients as c)
      .orderBy(c.ID)
  }.map(clients(c)).list.apply()

  /**
    *
    * @param name
    * @param discount
    * @param session
    * @return
    */
  def create(name: String, discount: Int)(implicit session:DBSession = AutoSession): client = {
    val m = clients.column
    val id = withSQL {
      insert.into(clients).namedValues(
        m.name -> name,
        m.discount -> discount
      )
    }.updateAndReturnGeneratedKey.apply()

    client(ID = id, name = name, discount = discount)
  }

  /**
    *
    * @param m
    * @param session
    * @return
    */
  def save(m: client)(implicit session: DBSession = AutoSession): client = {
    withSQL {
      update(clients).set(
        column.name -> m.name,
        column.discount -> m.discount
      ).where.eq(column.ID, m.ID)
    }.update.apply()
    m
  }

//  def destroy(ID:Long)(implicit session:DBSession = AutoSession): Unit = withSQL {
//    update(clients).set(column.deletedAt -> )
//  }

}


