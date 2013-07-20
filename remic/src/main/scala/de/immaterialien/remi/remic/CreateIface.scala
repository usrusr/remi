package de.immaterialien.remi.remic

import scala.xml._

import org.cojen.classfile._

import de.immaterialien.remi.binding

object CreateIface {
  def main(args:String*){
    val xhtml = (
        <html>
          <head></head>
          <body></body>
        </html>
    )
  }
  val interfaceModifier = Modifiers.getInstance(Modifiers.PUBLIC.getBitmask)
}

class CreateIface(root:Elem, name:String, pack:String, nodeType : Class[_], leafType : Class[_]) { import CreateIface._
  def cleanClass(cp:String)={
    var r = cp
    r = "^\\W+".r.replaceAllIn(r, "")
    r = "^[^a-zA-Z]+".r.replaceAllIn(r, "")
    r = "^(\\w)".r.replaceAllIn(r, {mtch =>
      mtch.group(1).toUpperCase
    })
    r = "\\W+$".r.replaceAllIn(r, "")
    r = "\\W+(\\w)".r.replaceAllIn(r, {mtch =>
      mtch.group(1).toUpperCase
    })
    r
  }
  def cleanPack(rp:String)={ 
    var r = rp
    r = "^\\W+".r.replaceAllIn(r, "")
    r = "\\W+$".r.replaceAllIn(r, "")
    r = "\\W+".r.replaceAllIn(r, ".")
    r.toLowerCase
  }
  val cf = new ClassFile(cleanPack(pack)+"."+cleanClass(name))
  var toWrite = cf :: Nil
  cf.setModifiers(interfaceModifier)
  
  val tree = descend(root)

  def addDeeper(parent:ClassFile, sub:Deeper):Unit= sub match {
    case Deeper(field, Nil) => {

      val args = Array[TypeDesc](TypeDesc.forClass(leafType))
      parent.addMethod(Modifiers.PUBLIC, field, MethodDesc.forArguments(TypeDesc.VOID, args))
    }
    case Deeper(interface, children) => {
      val df = parent.addInnerClass(null, interface) 
      df.setModifiers(interfaceModifier)
      toWrite = df :: toWrite
      for(c <- children) addDeeper(df, c)
    }
  } 
  
  case class Deeper(name:String, deepers:List[Deeper])
  def descend(elem:Elem):List[Deeper]={
    
    var deepers = List[Deeper]() 
    
    for(child <- elem.elements if(child.isInstanceOf[Elem])){
      deepers = descend(child.asInstanceOf[Elem]).reverse ::: deepers
    }
    deepers = deepers.reverse
    
    val name = hasName(elem)
    name map {
      n=> List(Deeper(n, deepers))
    } getOrElse deepers
  }
  def hasName(elem:Elem):Option[String]={
    val name = "remi"
//    elem.attributes.filter{att=>
//      att.get(name).isDefined
//    }.map{att=>
//      att.get(name).get
//    }.headOption
    
    val attV  = elem.attributes.get(name).headOption.map{ node=>
      node.text
    }
    attV
  }
}