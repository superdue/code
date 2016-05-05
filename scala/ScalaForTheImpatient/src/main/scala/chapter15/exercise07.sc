package chapter15

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  import annotation.tailrec

  // 编译报错
  /*
  class Test {
    @tailrec
    def sum2(xs: Seq[Int], partial: BigInt): BigInt = {
      if (xs.isEmpty) partial else sum2(xs.tail, xs.head + partial)
    }
  }
  */
  
  // 修改
	object Test{
	  @tailrec
	  def sum2(xs : Seq[Int],partial : BigInt) : BigInt = {
	    if (xs.isEmpty) partial else sum2(xs.tail,xs.head + partial)
	  }
	}
}