import sbtassembly.MergeStrategy

excludeLintKeys in Global ++= Set(idePackagePrefix)


inThisBuild {
  List(
    organization := "org.aulune",
    scalaVersion := "3.3.6",
    semanticdbEnabled := true,
    version := "0.1.0-SNAPSHOT",
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-Wnonunit-statement",
      "-Werror",
      "-Xmax-inlines:64",
    ),
    assembly / assemblyMergeStrategy := mergeStrategy,
  )
}


def mergeStrategy: String => MergeStrategy = {
  case PathList("META-INF", "services", _*) => MergeStrategy.concat
  case PathList("META-INF", _*)             => MergeStrategy.discard
  case "module-info.class"                  => MergeStrategy.discard
  case x => MergeStrategy.defaultMergeStrategy(x)
}


lazy val app = (project in file("."))
  .settings(
    name := "app",
    idePackagePrefix := Some("org.aulune.rajtigo"),
    assembly / mainClass := Some("org.aulune.rajtigo.App"),
    libraryDependencies ++= http4sDeps ++ Seq(
      "ch.qos.logback" % "logback-classic" % logbackVersion,
    ),
  )


val http4sVersion = "0.23.30"
val logbackVersion = "1.5.18"


val http4sDeps = Seq(
  "org.http4s" %% "http4s-ember-server",
  "org.http4s" %% "http4s-dsl",
).map(_ % http4sVersion)
