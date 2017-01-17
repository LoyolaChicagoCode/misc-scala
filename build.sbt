name := "misc-scala"

version := "0.0.2"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5",
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.scalatest" %% "scalatest" % "2.2.6" % Test,
  "org.scalacheck" %% "scalacheck" % "1.12.6" % Test
)
