package chapter15

object exercise06_2 {

  // http://www.ivanpig.com/blog/?cat=84&paged=1
  // 这里只有一个线程修改Boolean字段，所以字段是否为volatile应该是没有区别的

  import scala.actors.Actor

  class T1(obj: Obj) extends Actor {
    def act() {
      println("T1 is waiting")
      Thread.sleep(5000)
      obj.flag = true
      println("T1 set flag = true")
    }
  }

  class T2(obj: Obj) extends Actor {
    def act() {
      var f = true
      while (f) {
        if (obj.flag) {
          println("T2 is end")
          f = false
        } else {
          println("T2 is waiting")
          Thread.sleep(1000)
        }
      }
    }
  }

  class Obj {
    //  @volatile
    var flag: Boolean = false
  }

  def main(args: Array[String]): Unit = {

    val obj = new Obj()
    val t1 = new T1(obj)
    val t2 = new T2(obj)
    t1.start()
    t2.start()

  }

}