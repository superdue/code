package chapter13

object excercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def mkString[T](s: Seq[T], sep: String = ","): String =
    s.map(_.toString).reduceLeft(_ + sep + _)     //> mkString: [T](s: Seq[T], sep: String)String
    
  mkString(List("c","b","a"))                     //> res0: String = c,b,a
}