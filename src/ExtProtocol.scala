package org.streum.sbinary

import sbinary._
import DefaultProtocol._

object ExtProtocol extends DefaultProtocol {

  // new URI(u.toString()).equals(u)
  // http://docs.oracle.com/javase/6/docs/api/java/net/URI.html

  import java.net.URI
  
  //implicit val uriFormat = wrap[URI,String]( _.toString, new URI(_) )

  implicit val uriFormat = viaString[URI]( new URI(_) )
  
  import java.util.Date
  
  implicit val dateFormat = wrap[Date,Long]( _.getTime, new Date(_) )

  case class Job( company: String, title: String )
  case class Person( name: String, age: Int, job: Option[Job] )

  implicit val jobFormat = asProduct2( Job )( Job.unapply(_).get )
  implicit val PersonFormat = asProduct3( Person )( Person.unapply(_).get )

//  implicit def vectorFormat[T:Format:Manifest] = 
//    wrap[Vector[T],Array[T]]( _.toArray, ts => Vector( ts:_* ) )

  implicit def vectorFormat[T:Format:Manifest] = 
    viaArray[Vector[T],T]( ts => Vector( ts:_* ) )

  abstract class Color( val red: Int, val green: Int, val blue: Int ) {
    override def toString = "Color(" + red + "," + green + "," + blue + ")"
    override def equals( a: Any ) = a match {
      case c: Color => c.red == red && c.green == green && c.blue == blue 
      case _ => false
    }
  }
  object White extends Color( 255, 255, 255 )
  object Blue extends Color( 0, 0, 255 )
  class RGB( r: Int, g: Int, b: Int ) extends Color(r,g,b)

  implicit val rgbFormat = {
    val rgb2tuple3 = (rgb:RGB) => (rgb.red, rgb.green, rgb.blue)
    val tuple32rgb = 
      (tuple3:(Int,Int,Int)) => new RGB(tuple3._1, tuple3._2, tuple3._3)
    wrap[RGB,(Int,Int,Int)](rgb2tuple3, tuple32rgb )
  }

  implicit val colorFormat = 
    asUnion( White, Blue, rgbFormat )

}
