package chapter15

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // <https://github.com/alanktwong/scala-for-impatient/blob/master/core/src/main/scala/scala/impatient/Exercises15.scala>
  
  @deprecated(message = "This class is Deprecated")
  class Deprecated(var name: String) {
    @deprecated
    var description = name
    
    @deprecated(message = "this method is obsolete")
    def whoAmI: String = {
      name
    }
    
    def print(@deprecated something: String): String = {
      val x = (name, description, something)
      x.toString
    }
    
    def sayHi(@deprecatedName('world) sender: String) {
      @deprecated
      var greeter = sender
      println("Hello " + sender)
    }
  }
}