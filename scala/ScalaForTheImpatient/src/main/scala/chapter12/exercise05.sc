package chapter12

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def largest(fun: (Int) => Int, inputs: Seq[Int]): Int =
    inputs.map(fun(_)).max                        //> largest: (fun: Int => Int, inputs: Seq[Int])Int
    
  largest(x => 10 * x - x * x, 1 to 10)           //> res0: Int = 25
  
  // 另外一种解法
  
  def largest2(fun: (Int) => Int, inputs: Seq[Int]): Int =
    (0 /: inputs) { (max, each) => if (max > fun(each)) max else fun(each) }
                                                  //> largest2: (fun: Int => Int, inputs: Seq[Int])Int
  
  largest2(x => 10 * x - x * x, 1 to 10)          //> res1: Int = 25
  
  // 或者使用reduceLeft
  def largest3(fun: (Int) => Int, inputs: Seq[Int]): Int =
    fun(inputs.reduceLeft((a, b) => if (fun(a) > fun(b)) a else b))
                                                  //> largest3: (fun: Int => Int, inputs: Seq[Int])Int
  largest3(x => 10 * x - x * x, 1 to 10)          //> res2: Int = 25
}