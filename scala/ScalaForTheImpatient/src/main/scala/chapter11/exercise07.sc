package chapter11

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 整合了 <https://github.com/hempalex/scala-impatient/blob/master/Chapter11/07.scala> 和 <http://www.ivanpig.com/blog/?cat=84&paged=1>

  class BigSequence {
    var num = new Array[Int](64)
    for (i <- 0 until num.length) {
      num(i) = -1
    }
    def pack(): Long = {
      num.filter(_ >= 0).mkString.toLong
    }
    //override def toString = "%64s".format(pack().toBinaryString).replace(" ", "0")
    override def toString = num.mkString(" ")
    def apply(bit: Int): Int = num(bit)
    def update(bit: Int, state: Int) {
      num(bit) = state
    }
  }
  object BigSequence {
    	def apply(num: Int): BigSequence = {
    	  val b = new BigSequence
    	  var i = 0
    	  num.toString.foreach {
    	    n =>
    	    b.num(i) = n.getNumericValue
    	    i += 1
    	  }
    	  b
    	}
  }
  
  val b = BigSequence(30123)                      //> b  : chapter11.exercise07.BigSequence = 3 0 1 2 3 -1 -1 -1 -1 -1 -1 -1 -1 -1
                                                  //|  -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 
                                                  //| -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
  b(0)                                            //> res0: Int = 3
  b(1)                                            //> res1: Int = 0
  b(2)                                            //> res2: Int = 1
  b(3)                                            //> res3: Int = 2
  b(4)                                            //> res4: Int = 3
  b(5)                                            //> res5: Int = -1
  
  b(0) = 9
  
  b                                               //> res6: chapter11.exercise07.BigSequence = 9 0 1 2 3 -1 -1 -1 -1 -1 -1 -1 -1 -
                                                  //| 1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
                                                  //|  -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
  
}