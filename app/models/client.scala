package models

import play.api.mvc.Session
import scalikejdbc._
import org.joda.time.DateTime
import SQLInterpolation._

/**
  * Created by kuulart on 16.24.2.
  */
case class client(
    ID: Long,
    name: String,
    discount: Int
) {
  def save()(implicit session: DBSession = AutoSession): client = clients.save(this)(session)
  //def destroy()(implicit session: DBSession = clients.AutoSession): Unit = clients.destroy(ID)(session)
}

object clients extends SQLSyntaxSupport[client] {
  def apply(c: SyntaxProvider[client])(rs: WrappedResultSet): client = apply(c.resultName)(rs)
  def apply(c: ResultName[client])(rs: WrappedResultSet): client = new client(
    ID = rs.get(c.ID),
    name = rs.get(c.name),
    discount = rs.get(c.discount)
  )

  val c = clients.syntax("c")
//  private val isNotDeleted = sqls.isNull(c.deletedAt)
  def find(ID: Long)(implicit session: DBSession = autoSession): Option[client] = withSQL {
  select.from(clients as c).where.eq(c.ID, ID)
  }.map(clients(c)).single.apply()

  def findAll()(implicit session: DBSession = autoSession): List[client] = withSQL {
    select.from(clients as c)
      .orderBy(c.ID)
  }.map(clients(c)).list.apply()


  def create(name: String, discount: Int)(implicit session:DBSession = AutoSession): client = {
    val id = withSQL {
      insert.into(clients).namedValues(
        column.name -> name,
        column.discount -> discount
      )
    }.updateAndReturnGeneratedKey.apply()

    client(ID = id, name = name, discount = discount)
  }

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


