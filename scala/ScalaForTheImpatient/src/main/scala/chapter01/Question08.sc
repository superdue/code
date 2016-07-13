package ch01

object Question08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 8. 创建随机文件的方式之一是生成一个随机的 BigInt，然后将它转换成三十六进制，输出类似 "qsnvbevtomcj38o06ku1" 这样的字符串。查阅 Scaladoc，找到在 Scala 中实现该逻辑的办法。
  BigInt.probablePrime(150, scala.util.Random).toString(36)
                                                  //> res0: String = 11e8zopdj7n8d1czb3q7a5bb5q7fd7
}