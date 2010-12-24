import sbt._

class RemiProject(info: ProjectInfo) extends ParentProject(info) 
with de.element34.sbteclipsify.Eclipsify 
{
  
  lazy val api = project("api", "client and binding API")
//  lazy val remic = project("remic", "remi markup compiler", info => new DefaultProject(info){
////      api, 
//      override def libraryDependencies = Set(
////          api, 
//          "org.cojen" % "cojen" % "2.0" % "default"
//      )
//    }, api
//  )
  lazy val remic = project("remic", "remi markup compiler", api, bindings)
  lazy val bindings = project("bindings", "API bindings", new Bindings(_))

 // lazy val bindings = new Bindings(info)
//  lazy val exampleBinding = project("exampleBinding", "dummy backend binding", api)
  class Bindings(_) extends ParentProject(_)
with de.element34.sbteclipsify.Eclipsify 
  {
    lazy val dummy = project("dummy", "remi API example binding", api)
  }
  override def libraryDependencies = Set(
	"org.scala-tools.testing" % "scalatest" % "1.0" % "test"
  )

}