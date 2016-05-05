package ch07

object Question03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 3. 编写一个包 random，加入函数 nextInt(): Int、nextDouble(): Double 和 setSeed(seed: Int): Unit。生成随机数的算法采用线性同余生成器：
  //    后值 = （前值 x a + b） mod pow(2, n)
  //    其中，a = 1664525，b = 1013904223，n = 32，前值的初始值为 seed。
}