package chapter13

object excercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = Array("Tom", "Fred", "Harry")           //> x  : Array[String] = Array(Tom, Fred, Harry)
  val y = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
                                                  //> y  : scala.collection.immutable.Map[String,Int] = Map(Tom -> 3, Dick -> 4, H
                                                  //| arry -> 5)
  def func(x: Array[String], y: Map[String, Int]): Array[Int] = x.flatMap(y.get(_))
                                                  //> func: (x: Array[String], y: Map[String,Int])Array[Int]
  func(x, y)                                      //> res0: Array[Int] = Array(3, 5)
  
 
  List("a", "b", "c").flatMap(Option(_))          //> res1: List[String] = List(a, b, c)
  
  // 另外一种解法，这个返回的是Set，而上面的解法返回的是List。而题目要求返回Array(3, 5)。
  
  def getIntegersThatMapped(strings: Seq[String], map: Map[String, Int]): Set[Int] = {
    val intersection = map.keySet.intersect(strings.toSet)
    val result = intersection.map { map.get(_).get }
    result
  }                                               //> getIntegersThatMapped: (strings: Seq[String], map: Map[String,Int])Set[Int]
  
  getIntegersThatMapped(x, y)                     //> res2: Set[Int] = Set(3, 5)
}