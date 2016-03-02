package settings

import scalikejdbc._, config._

trait DBSettings {
  DBSettings.initialize()
}

object DBSettings {

  private var isInitialized = false

  def initialize(): Unit = this.synchronized {
    if (isInitialized) return
    DBs.setupAll()
    GlobalSettings.loggingSQLErrors = false
    //GlobalSettings.sqlFormatter = SQLFormatterSettings("utils.HibernateSQLFormatter")
    DBInitializer.run()
    isInitialized = true
  }

}

object DBInitializer {

  def run() {
    NamedDB('testdb) readOnly { implicit s =>
      try {
        sql"select 1 from programmer limit 1".map(_.long(1)).single.apply()
      } catch {
        case e: java.sql.SQLException =>
          NamedDB('testdb) autoCommit { implicit s =>
            sql"""

create table clients(
ID INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
discount TINYINT(3) unsigned,
PRIMARY KEY ( ID )
);

INSERT INTO clients values (null, 'prosound', 20);

   """.execute.apply()
          }
      }
    }
  }

}
