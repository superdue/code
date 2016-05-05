package chapter13

object excercise01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val s = "Mississippi"                           //> s  : String = Mississippi
  
  def indexes(s: String): scala.collection.mutable.Map[Char, scala.collection.mutable.SortedSet[Int]] = {
    val rec = scala.collection.mutable.HashMap[Char, scala.collection.mutable.SortedSet[Int]]()
    for ((c, i) <- s.zipWithIndex) rec(c) = rec.getOrElse(c, scala.collection.mutable.SortedSet[Int]()) + i
    rec
  }                                               //> indexes: (s: String)scala.collection.mutable.Map[Char,scala.collection.mutab
                                                  //| le.SortedSet[Int]]
  indexes(s)                                      //> res0: scala.collection.mutable.Map[Char,scala.collection.mutable.SortedSet[I
                                                  //| nt]] = Map(M -> TreeSet(0), s -> TreeSet(2, 3, 5, 6), p -> TreeSet(8, 9), i 
                                                  //| -> TreeSet(1, 4, 7, 10))
   
}