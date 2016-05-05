package chapter12

object exercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int =
    inputs.foldLeft(inputs.head)((max, each) => if (fun(max) > fun(each)) max else each)
                                                  //> largestAt: (fun: Int => Int, inputs: Seq[Int])Int
    
  largestAt(x => 10 * x - x * x, 1 to 10)         //> res0: Int = 5
  
  // 用reduceLeft会有一种异常情况，即inputs元素为1个。
}