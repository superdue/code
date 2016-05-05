package chapter13

object excercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // empty
  
  // 网上找了一个人写的解法：<https://github.com/alanktwong/scala-for-impatient/blob/master/core/src/main/scala/scala/impatient/Exercises13.scala>
  
  // 这种方法有严重问题！不用看了。
  
  import scala.io.Source
  def source(sourceName: String): Source = {
    val source = Source.fromFile(sourceName: String, "UTF-8")
    source
  }                                               //> source: (sourceName: String)scala.io.Source
  
  def readTokens(filename: String): Array[String] = {
    var src = source(filename)
    val lineIterator = src.getLines
    val lines = lineIterator.toArray
    val content = src.mkString
    val tokens: Array[String] = content.split("\\s+")
    tokens
  }                                               //> readTokens: (filename: String)Array[String]
  
  def wordCountFold(filename: String): Map[String, Int] = {
    val tokens = readTokens(filename)
    var map = scala.collection.immutable.SortedMap[String, Int]()
    tokens.foldLeft(map) {
      (m, c) => m + ( c -> (m.getOrElse(c, 0) + 1 ) )
    }
  }                                               //> wordCountFold: (filename: String)Map[String,Int]
  
  def readFiles(files: Seq[String]) = {
    import scala.actors.Actor._
    
    val readFileActor = actor {
      while (true) {
        receive {
          case s: String => {
            val wordCount = wordCountFold(s)
            // now update one of the frequencies maps
          }
          case c: Char => {
            val freq1 = new scala.collection.mutable.HashMap[Char,Int] with scala.collection.mutable.SynchronizedMap[Char,Int]
            import scala.collection.JavaConversions.asScalaConcurrentMap
            //val freq2: scala.collection.mutable.ConcurrentMap[Char,Int] = new java.util.concurrent.ConcurrentHashMap[Char,Int]
            // Read performance of freq2 > freq1
            freq1.getOrElse(c, 0) + 1
            //freq2.getOrElse(c, 0) + 1
          }
          case _ => println("Do nothing")
        }
      }
    }
    for (file <- files) {
    }
  }                                               //> readFiles: (files: Seq[String])Unit
  
  
  // 然后在网上找了另外一个人写的 <http://www.ivanpig.com/blog/?p=509>
  
  // 他的答案是：并发问题，并发修改集合不安全.修改后的代码和修改前的代码没有什么太大的区别.
}