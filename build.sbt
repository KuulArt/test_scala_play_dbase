name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalikejdbc" %% "scalikejdbc"       % "2.3.5",
  "com.h2database"  %  "h2"                % "1.4.191",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.3.5",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.4.3",

  "org.scalikejdbc" %% "scalikejdbc-test"   % "2.3.5"   % "test",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.3",
  "mysql" % "mysql-connector-java" % "5.1.18",

    specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := false