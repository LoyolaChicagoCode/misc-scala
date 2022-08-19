name := "misc-scala"

version := "0.0.3"

scalaVersion := "3.1.3"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Yexplicit-nulls")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "com.novocode"           %  "junit-interface"          % "0.11"   % Test,
  "org.scalatest"          %% "scalatest"                % "3.2.13"  % Test,
  "org.scalacheck"         %% "scalacheck"               % "1.16.0" % Test
)

scalacOptions ++= Seq("-rewrite", "-new-syntax")
