import sbt._
class RemiPlugins(info: ProjectInfo) extends PluginDefinition(info)
{
  lazy val eclipse = "de.element34" % "sbt-eclipsify" % "0.7.0"

}