package week2

import scala.annotation.tailrec

object test {
  
  // nice for myself!
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)
  }                                               //> sum: (f: Int => Int)(a: Int, b: Int)Int
  
  def sum2(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    loop(a, 0)
  }                                               //> sum2: (f: Int => Int)(a: Int, b: Int)Int
  
  def sumInts(a: Int, b: Int) = sum(x => x * x)(a, b)
                                                  //> sumInts: (a: Int, b: Int)Int
  
  sumInts(3, 5)                                   //> res0: Int = 50
  
  
  
  def sum3(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }                                               //> sum3: (f: Int => Int)(Int, Int) => Int
}