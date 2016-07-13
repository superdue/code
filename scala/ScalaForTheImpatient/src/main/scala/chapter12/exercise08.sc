package chapter12

object exercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val a = Array("a", "b", "c")                    //> a  : Array[String] = Array(a, b, c)
  val b = Array("d", "e", "f")                    //> b  : Array[String] = Array(d, e, f)
  
  a.corresponds(b)(_.equalsIgnoreCase(_))         //> res0: Boolean = false
  
  def doTheStringsHaveLengths(strings: Array[String], lengths: Array[Int]): Boolean =
    strings.corresponds(lengths) { _.length == _ }//> doTheStringsHaveLengths: (strings: Array[String], lengths: Array[Int])Boolea
                                                  //| n
    
  doTheStringsHaveLengths(Array("hello","world"), Array(5,5))
                                                  //> res1: Boolean = true
}