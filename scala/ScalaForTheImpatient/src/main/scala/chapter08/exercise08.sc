package chapter08

object exercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }

  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don't want to reveal name...
    override val toString = "secret" //... or class name
  }
  
  // 总共两个。Person中取得的是传入的name,而SecretAgent中取得的是默认的"secret"
}