import commondeps.Dependencies._

val vAll = Versions(versions, libraries, scalacPlugins)


lazy val effx = crossProject.crossType(CrossType.Pure).in(file("shared"))
  .settings(name := "effx", organization := "com.github.benhutchison")
  .settings(scalaVersion := vAll.vers("scalac"))
  .settings(addCompilerPlugins(vAll, "kind-projector"))
  .settings(addLibs(vAll,
    "cats-core", "eff", "monocle-core", "monocle-generic",  "monocle-macro", "mouse"
  ))
  .settings(scalacOptions ++= scalacAllOptions)

lazy val projJS = effx.js
lazy val projJVM = effx.jvm

lazy val effxRoot = project.in(file(".")).
  aggregate(projJS, projJVM).
  settings(noPublishSettings)