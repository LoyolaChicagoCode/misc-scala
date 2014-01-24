name := "misc-scala"

version := "0.0.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps")

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.10" % "test",
  "org.scalatest" % "scalatest_2.10" % "2.0.1-SNAP" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.1" % "test"
)
