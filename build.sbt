import sbtassembly.AssemblyPlugin.autoImport._
import sbt.Keys._

lazy val root = (project in file("."))
  .settings(
    name := "TIAScalaPriceBasket",
    version := "1.0",
    scalaVersion := "2.12.20",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.13.16",
      "org.scala-lang" % "scala-reflect" % "2.13.16",
      "org.scala-lang" % "scala-compiler" % "2.13.16",

      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scalacheck" %% "scalacheck" % "1.18.1" % Test
    ),
    assembly / test := {},
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @_*) => MergeStrategy.discard
      case _ => MergeStrategy.first
    }
  )
enablePlugins(AssemblyPlugin)
