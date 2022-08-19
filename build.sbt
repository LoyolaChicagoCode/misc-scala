name := "misc-scala"

version := "0.0.3"

scalaVersion := "3.1.3"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Yexplicit-nulls")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.0.0",
  "com.novocode"           %  "junit-interface"          % "0.11"   % Test,
  "org.scalatest"          %% "scalatest"                % "3.2.9"  % Test,
  "org.scalacheck"         %% "scalacheck"               % "1.15.4" % Test
)

scalacOptions ++= Seq("-rewrite", "-new-syntax")
