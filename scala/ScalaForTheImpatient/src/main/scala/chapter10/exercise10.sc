package chapter10

object exercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait IterableInputStream extends java.io.InputStream with Iterable[Byte] {

    class InputStreamIterator(outer: IterableInputStream) extends Iterator[Byte] {
      def hasNext: Boolean = outer.available() > 0
      def next: Byte = outer.read().toByte
    }

    def iterator: Iterator[Byte] = new InputStreamIterator(this)
  }

  val f = new java.io.FileInputStream("/Users/stephansun/Applications/scala/progfun-workspace/ScalaForTheImplatient/src/chapter10/exercise10.sc") with IterableInputStream
                                                  //> f  : java.io.FileInputStream with chapter10.exercise10.IterableInputStream =
                                                  //|  exercise10(112, 97, 99, 107, 97, 103, 101, 32, 99, 104, 97, 112, 116, 101, 
                                                  //| 114, 49, 48, 10, 10, 111, 98, 106, 101, 99, 116, 32, 101, 120, 101, 114, 99,
                                                  //|  105, 115, 101, 49, 48, 32, 123, 10, 32, 32, 112, 114, 105, 110, 116, 108, 1
                                                  //| 10, 40, 34, 87, 101, 108, 99, 111, 109, 101, 32, 116, 111, 32, 116, 104, 101
                                                  //| , 32, 83, 99, 97, 108, 97, 32, 119, 111, 114, 107, 115, 104, 101, 101, 116, 
                                                  //| 34, 41, 32, 47, 47, 62, 32, 87, 101, 108, 99, 111, 109, 101, 32, 116, 111, 3
                                                  //| 2, 116, 104, 101, 32, 83, 99, 97, 108, 97, 32, 119, 111, 114, 107, 115, 104,
                                                  //|  101, 101, 116, 10, 10, 32, 32, 116, 114, 97, 105, 116, 32, 73, 116, 101, 11
                                                  //| 4, 97, 98, 108, 101, 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109, 32,
                                                  //|  101, 120, 116, 101, 110, 100, 115, 32, 106, 97, 118, 97, 46, 105, 111, 46, 
                                                  //| 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109, 32, 119, 105, 116, 104, 
                                                  //| 32, 73, 116, 101, 114, 9
                                                  //| Output exceeds cutoff limit.

  for (b <- f) println(b.toChar)

}