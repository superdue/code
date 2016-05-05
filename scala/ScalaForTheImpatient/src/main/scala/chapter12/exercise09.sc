package chapter12

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 没有柯里化则不能使用前一个练习里的代码方式来调用
  
  def corresponds[A, B](a: Seq[A], b: Seq[B], f: (A, B) => Boolean) =
    (a zip b).map(x => f(x._1, x._2)).count(!_) == 0
                                                  //> corresponds: [A, B](a: Seq[A], b: Seq[B], f: (A, B) => Boolean)Boolean
    
  def doTheStringsHaveLengths(strings: Array[String], lengths: Array[Int]): Boolean =
    corresponds(strings, lengths, (x: String, y: Int) => x.length == y)
                                                  //> doTheStringsHaveLengths: (strings: Array[String], lengths: Array[Int])Boolea
                                                  //| n
    
  doTheStringsHaveLengths(Array("hello","world"), Array(5,5))
                                                  //> res0: Boolean = true
}