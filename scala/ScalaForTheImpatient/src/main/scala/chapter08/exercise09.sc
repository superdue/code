package chapter08

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Creature {
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
  }

  class Ant extends Creature {
    override val range = 2
  }
  
  // 在Ant中使用def没有问题。但是如果使用val则无法编译。因为val只能重写不带参数的def。这里的def是带参数的
}