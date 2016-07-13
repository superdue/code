package chapter18

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  trait A1 {
    private val x: Int = 1
    val y: Int = 1
  }
  
  trait A2 {
    private val x: Int = 1
  }
  
  class B extends A1 with A2 {
  }
  
  val b = new B()                                 //> b  : chapter18.test.B = chapter18.test$$anonfun$main$1$B$1@a94884d
  b.y                                             //> res0: Int = 1
}