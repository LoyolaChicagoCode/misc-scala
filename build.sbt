name := "misc-scala"

version := "0.0.3"

scalaVersion := "3.3.0"

scalacOptions += "@.scalacOptions.txt"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0",
  "com.novocode"           %  "junit-interface"          % "0.11"   % Test,
  "org.scalatest"          %% "scalatest"                % "3.2.16" % Test,
  "org.scalacheck"         %% "scalacheck"               % "1.17.0" % Test
)
