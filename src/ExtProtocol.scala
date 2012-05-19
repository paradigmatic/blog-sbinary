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

}
