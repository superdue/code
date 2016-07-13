package ch02

object Question01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 1. 一个数字如何为正数，则它的 signum 为 1 ；如何为负数，则 signum 为 -1 ；如果是 0， 则 signum 为 0 。编写一个函数来计算这个值。
  def signum(x: Int): Int =
    if (x > 0) 1
    else if (x < 0) -1
    else 0                                        //> signum: (x: Int)Int
    
  signum(-20)                                     //> res0: Int = -1
  signum(20)                                      //> res1: Int = 1
  signum(0)                                       //> res2: Int = 0
}