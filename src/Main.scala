package org.streum.sbinary

import sbinary._
import DefaultProtocol._
import ExtProtocol._

object Stub extends App with SBinaryHelper {

  checkFormat( 2.0 )
  
  checkFormat( Option(2.0) )
  
  val uri = new java.net.URI( "http://paradigmatic.streum.org/" )
  checkFormat( uri )

  val now = new java.util.Date()
  checkFormat( now )

  val job1 = Job( "Yahoo", "CEO" )
  checkFormat( job1 )
  
  val person1 = Person( "Jane Doe", 43, Some(job1) )
  checkFormat( person1 )

  val ary = Vector( 1, 2, 3 )
  checkFormat( ary )
  //checkFormat[Either[Double,String]]( Left(2.0) )

  val green = new RGB( 0, 255, 0 )
  checkFormat( green )

  checkFormat[Color]( Blue )

  checkFormat( Vector( green, White ) )
}
