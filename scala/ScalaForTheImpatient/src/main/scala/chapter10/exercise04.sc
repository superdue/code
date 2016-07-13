package chapter10

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Logger {
    def log(msg: String) = {}
  }

  trait ConsoleLogger extends Logger {
    override def log(msg: String) = Console.println(msg)
  }

  trait CaesarLogger extends Logger {
    val shift: Int = 3
    override def log(msg: String) = {
      super.log((for (x <- msg) yield (x + shift).toChar).mkString)
      // more elegant
      super.log(msg.map(_ + shift).map(_.toChar).mkString)
      // speedup but less elegant
      super.log(msg.map((x: Char) => (x + shift).toChar).mkString)
    }
  }

  class Sample extends Logger {
    def doSomeWork() = {
      log("Some Log Message")
    }
  }

  val x = new Sample with ConsoleLogger           //> x  : chapter10.exercise04.Sample with chapter10.exercise04.ConsoleLogger = c
                                                  //| hapter10.exercise04$$anonfun$main$1$$anon$3@1d3c468a
  x.doSomeWork                                    //> Some Log Message

  val y = new Sample with ConsoleLogger with CaesarLogger
                                                  //> y  : chapter10.exercise04.Sample with chapter10.exercise04.ConsoleLogger wit
                                                  //| h chapter10.exercise04.CaesarLogger = chapter10.exercise04$$anonfun$main$1$$
                                                  //| anon$1@48ee22f7
  y.doSomeWork                                    //> Vrph#Orj#Phvvdjh
                                                  //| Vrph#Orj#Phvvdjh
                                                  //| Vrph#Orj#Phvvdjh

  val z = new { override val shift = -3 } with Sample with ConsoleLogger with CaesarLogger
                                                  //> z  : chapter10.exercise04.Sample with chapter10.exercise04.ConsoleLogger wit
                                                  //| h chapter10.exercise04.CaesarLogger = chapter10.exercise04$$anonfun$main$1$$
                                                  //| anon$2@2efb56b1
  z.doSomeWork                                    //> PljbIldJbpp^db
                                                  //| PljbIldJbpp^db
                                                  //| PljbIldJbpp^db
}