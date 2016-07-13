package chapter05

object exercise05 {

  import scala.reflect.BeanProperty

  class Student(@BeanProperty var Name: String, @BeanProperty var id: Long)

  // scalac exercise05.scala
  // javap -private Student

}