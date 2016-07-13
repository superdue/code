package chapter11

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  object RichFile {
    def unapply(s: String) = {
      val pos = s.lastIndexOf("/")
      if (pos == -1) None
      else {
        val filename = s.substring(pos + 1).split("\\.")
        Some((s.substring(0, pos), filename(0), filename(1)))
      }
    }
  }

  val RichFile(path, name, extension) = "/home/user/test.txt"
                                                  //> path  : String = /home/user
                                                  //| name  : String = test
                                                  //| extension  : String = txt

  "Path: %s, File: %s, Extension: %s".format(path, name, extension)
                                                  //> res0: String = Path: /home/user, File: test, Extension: txt
}