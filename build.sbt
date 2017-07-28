lazy val root = project.in(file(".")).
  enablePlugins(ScalaJSPlugin)

name := "Gadgets"

normalizedName := "gadgets"

version := "0.1"

organization := "org.querki"

scalaVersion := "2.12.2"

crossScalaVersions := Seq("2.11.11", "2.12.2")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "com.lihaoyi" %%% "scalarx" % "0.3.2",
  "com.lihaoyi" %%% "scalatags" % "0.6.5",
  "org.querki" %%% "jquery-facade" % "1.0",
  "org.querki" %%% "squery" % "0.1"
)

homepage := Some(url("http://www.querki.net/"))

licenses += ("MIT License", url("http://www.opensource.org/licenses/mit-license.php"))

scmInfo := Some(ScmInfo(
    url("https://github.com/jducoeur/gadgets"),
    "scm:git:git@github.com:jducoeur/gadgets.git",
    Some("scm:git:git@github.com:jducoeur/gadgets.git")))

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <developers>
    <developer>
      <id>jducoeur</id>
      <name>Mark Waks</name>
      <url>https://github.com/jducoeur/</url>
    </developer>
  </developers>
)

pomIncludeRepository := { _ => false }
