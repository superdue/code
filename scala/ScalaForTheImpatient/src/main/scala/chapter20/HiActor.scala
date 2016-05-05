package chapter20

import scala.actors.Actor

class HiActor extends Actor {

  def act() {
    while (true) {
      receive {
        case "Hi" => println("Hello")
      }
    }
  }

}

object HiActor {
  def main(args: Array[String]) {
    val actor1 = new HiActor
    actor1.start()
    
    actor1 ! "Hi"
  }
}