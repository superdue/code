package ch01

object Question07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 7. 为了在使用 probablePrime(100, Random) 获取随机素数时不在 probablePrime 和 Random 之前使用任何限定符，你需要引入什么？
  
  import BigInt.{probablePrime}
  import scala.util.{Random}
  probablePrime(100, Random)                      //> res0: scala.math.BigInt = 876557864356298397114287140631
}