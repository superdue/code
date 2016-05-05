package chapter10

object exercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // read http://stackoverflow.com/questions/10169016/stackoverflow-when-use-bufferedinputstream-as-a-trait-in-scala
  import java.io.{ InputStream, FileInputStream }

  trait Buffering {
    this: InputStream =>

    val BUF_SIZE: Int = 5
    private val buf = new Array[Byte](BUF_SIZE)
    private var bufsize: Int = 0
    private var pos: Int = 0

    override def read(): Int = {
      if (pos >= bufsize) {
        bufsize = this.read(buf, 0, BUF_SIZE)
        if (bufsize > 0) -1
        pos = 0
      }
      pos += 1
      buf(pos - 1)
    }
  }

  val f = new FileInputStream("/Users/stephansun/Applications/scala/progfun-workspace/ScalaForTheImplatient/src/chapter10/exercise08.sc") with Buffering
                                                  //> f  : java.io.FileInputStream with chapter10.exercise08.Buffering = chapter10
                                                  //| .exercise08$$anonfun$main$1$$anon$1@19e3118a

  for (i <- 1 to 10) println(f.read())            //> 112
                                                  //| 97
                                                  //| 99
                                                  //| 107
                                                  //| 97
                                                  //| 103
                                                  //| 101
                                                  //| 32
                                                  //| 99
                                                  //| 104
}