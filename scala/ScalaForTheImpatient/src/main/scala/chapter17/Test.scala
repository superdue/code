package chapter17

object Test {
  
  class Pair[T](val first: T, val second: T) {
    def replaceFirst[R >: T](newFirst: R) = new Pair[R](newFirst, second)
  }

  def main(args: Array[String]): Unit = {
    println("hello")
  }
}