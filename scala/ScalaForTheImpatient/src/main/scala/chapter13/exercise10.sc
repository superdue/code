package chapter13

object excercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // https://github.com/hempalex/scala-impatient/blob/master/Chapter13/10.scala
  
  val str = io.Source.fromFile("/Users/stephansun/Desktop/1.txt").mkString
                                                  //> str  : String = "abcaba
                                                  //| "
  
  def printMillis(msg: String)(block: => Unit) {
    val start = System.currentTimeMillis()
    block
    val end = System.currentTimeMillis()
    println(msg.format(end - start))
  }                                               //> printMillis: (msg: String)(block: => Unit)Unit
  
  printMillis("Using mutable collection: %d ms") {
    var freq = new scala.collection.mutable.HashMap[Char, Int]
    for (c <- str) freq(c) = freq.getOrElse(c, 0) + 1
    println(freq.toSeq.sorted)
  }                                               //> ArrayBuffer((
                                                  //| ,1), (a,3), (b,2), (c,1))
                                                  //| Using mutable collection: 26 ms
  
  printMillis("Using immutable collection: %d ms") {
    var freq = str.map(c => (c, 1)).groupBy(_._1).map(x => (x._1, x._2.length))
    println(freq.seq.toSeq.sorted)
  }                                               //> ArrayBuffer((
                                                  //| ,1), (a,3), (b,2), (c,1))
                                                  //| Using immutable collection: 17 ms
  
  // 跟预想的结果不一样 :-(
  printMillis("Using mutable parallel collection: %d ms") {
    var freq = str.par.aggregate(new scala.collection.immutable.HashMap[Char, Int])(
      (x, c) => x + (c -> (x.getOrElse(c, 0) + 1)),
      (map1, map2) => map1 ++ map2.map { case (k, v) => k -> (v + map1.getOrElse(k, 0)) }
    )
    println(freq.toSeq.sorted)
  }                                               //> ArrayBuffer((
                                                  //| ,1), (a,3), (b,2), (c,1))
                                                  //| Using mutable parallel collection: 135 ms
}