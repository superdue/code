package chapter03

object excercise {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  // 给定一个整数的数组缓冲，我们想要移除第一个负数之外的所有负数
  import scala.collection.mutable.ArrayBuffer
  def removeAllNevigateNumberButNotFirstOne(a: ArrayBuffer[Int]): Unit = {
    var first = true
    var n = a.length
    var i = 0
    while (i < n) {
      if (a(i) >= 0) i += 1
      else {
        if (first) { first = false; i += 1 }
        // 从ArrayBuffer中remove元素并不高效
        else { a.remove(i); n-= 1 }
      }
    }
  }                                               //> removeAllNevigateNumberButNotFirstOne: (a: scala.collection.mutable.ArrayBuf
                                                  //| fer[Int])Unit
  
  val a = new ArrayBuffer[Int]()                  //> a  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  a += (1,-1,-2,-3,-4)                            //> res0: chapter03.excercise.a.type = ArrayBuffer(1, -1, -2, -3, -4)
  
  removeAllNevigateNumberButNotFirstOne(a)
  
  a                                               //> res1: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, -1)
  
  def removeAllNevigateNumberButNotFirstOne2(a: ArrayBuffer[Int]): Unit = {
    // first表示第一个负数
    var first = true
    var indexes = for (i <- 0 until a.length if first || a(i) >= 0) yield {
      if (a(i) < 0) first = false; i
    }
    for (j <- 0 until indexes.length) a(j) = a(indexes(j))
    a.trimEnd(a.length - indexes.length)
  }                                               //> removeAllNevigateNumberButNotFirstOne2: (a: scala.collection.mutable.ArrayBu
                                                  //| ffer[Int])Unit
  
  val a2 = new ArrayBuffer[Int]()                 //> a2  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
  a2 += (1,-1,-2,-3,-4)                           //> res2: chapter03.excercise.a2.type = ArrayBuffer(1, -1, -2, -3, -4)
  
  removeAllNevigateNumberButNotFirstOne2(a2)
  
  a2                                              //> res3: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, -1)
  
}