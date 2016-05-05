package chapter13

object excercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val s = "Mississippi"                           //> s  : String = Mississippi
  
  def indexes(s: String) = s.zipWithIndex.groupBy(_._1).map(x => (x._1, x._2.map(_._2)))
                                                  //> indexes: (s: String)scala.collection.immutable.Map[Char,scala.collection.imm
                                                  //| utable.IndexedSeq[Int]]
  
  indexes(s)                                      //> res0: scala.collection.immutable.Map[Char,scala.collection.immutable.Indexed
                                                  //| Seq[Int]] = Map(M -> Vector(0), s -> Vector(2, 3, 5, 6), p -> Vector(8, 9), 
                                                  //| i -> Vector(1, 4, 7, 10))
  
  // 另外一种解法，题目要求是字符到列表的不可变映射.
  
  import scala.collection.immutable._
  def indexString(string: String): SortedMap[Char, Set[Int]] = {
    (TreeMap[Char, Set[Int]]() /: string.zipWithIndex) { (map, each) =>
      val char = each._1
      val index = each._2
      map + ( char -> (map.getOrElse(char, Set[Int]()) + index) )
    }
  }                                               //> indexString: (string: String)scala.collection.immutable.SortedMap[Char,scala
                                                  //| .collection.immutable.Set[Int]]
  indexString(s)                                  //> res1: scala.collection.immutable.SortedMap[Char,scala.collection.immutable.S
                                                  //| et[Int]] = Map(M -> Set(0), i -> Set(1, 4, 7, 10), p -> Set(8, 9), s -> Set(
                                                  //| 2, 3, 5, 6))
  
}