package chapter10

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import java.io.{ InputStream, FileInputStream }

  trait Logger {
    def log(msg: String)
  }

  trait NoneLogger extends Logger {
    def log(msg: String) = {}
  }

  trait PrintLogger extends Logger {
    def log(msg: String) = println(msg)
  }

  trait Buffering {
    this: InputStream with Logger =>

    val BUF_SIZE: Int = 5
    private val buf = new Array[Byte](BUF_SIZE)
    private var bufsize: Int = 0
    private var pos: Int = 0

    override def read(): Int = {
      if (pos >= bufsize) {
        bufsize = this.read(buf, 0, BUF_SIZE)
        log("buffered %d bytes: %s".format(bufsize, buf.mkString(", ")))
        if (bufsize > 0) -1
        pos = 0
      }
      pos += 1
      buf(pos - 1)
    }
  }

  val f = new FileInputStream("/Users/stephansun/Applications/scala/progfun-workspace/ScalaForTheImplatient/src/chapter10/exercise08.sc") with Buffering with PrintLogger
                                                  //> f  : java.io.FileInputStream with chapter10.exercise09.Buffering with chapte
                                                  //| r10.exercise09.PrintLogger = chapter10.exercise09$$anonfun$main$1$$anon$1@1d
                                                  //| 807ca8

  for (i <- 1 to 10) println(f.read())            //> buffered 5 bytes: 112, 97, 99, 107, 97
                                                  //| 112
                                                  //| 97
                                                  //| 99
                                                  //| 107
                                                  //| 97
                                                  //| buffered 5 bytes: 103, 101, 32, 99, 104
                                                  //| 103
                                                  //| 101
                                                  //| 32
                                                  //| 99
                                                  //| 104
}