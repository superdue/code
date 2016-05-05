package demo

import scala.actors.Actor
import scala.actors.Actor._

object ActoDemo {

  class A extends Actor {
    def act() {
      while (true) {
        receive {
          case (resource: Actor, "hello") => resource ! "hello, world!"
          case "stop" => exit()
        }
      }
    }
  }

  class Resource extends Actor {

    def execute(actor: Actor, message: String): String = {
      actor ! (self, message)
      self receive {
        case "hello, world!" => "ok"
      }
    }

    def act() {
    }
  }

  def main(args: Array[String]): Unit = {
    
    val a = new A
    a.start

    val r = new Resource

    val rec = r.execute(a, "hello")

    println(rec)

  }

}