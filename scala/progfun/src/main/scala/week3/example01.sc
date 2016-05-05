object example01 {
  trait A {
    def sayHi: String
  }
  abstract class Foo {
    def sayHi = "foo hello"
  }
  class Bar extends Foo with A
  
  val b = new Bar                                 //> b  : example01.Bar = example01$Bar@a94884d
  b.sayHi                                         //> res0: String = foo hello
}