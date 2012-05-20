package org.streum.sbinary

import sbinary.Operations._
import sbinary._

trait SBinaryHelper {

  def duplicate[T:Format]( t: T ): T = 
    fromByteArray( toByteArray(t) )

  def isFormatOK[T:Format]( t: T ): Boolean =
    t == duplicate(t)

  def checkFormat[T:Format]( t: T ): Unit =
    if( isFormatOK( t ) )
      println( t + " is (un)serializable." )
    else
      println( t + " (un)serialization failed !" )

}
