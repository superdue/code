package chapter11

object exercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  object RichFile {
    def unapplySeq(s: String): Option[Seq[String]] = {
      if (s.trim == "") None else Some(s.trim.split("/"))
    }
  }

  val RichFile(first, middle, last) = "home/user/text.txt"
                                                  //> first  : String = home
                                                  //| middle  : String = user
                                                  //| last  : String = text.txt

  "First: %s, Middle: %s, Last: %s".format(first, middle, last)
                                                  //> res0: String = First: home, Middle: user, Last: text.txt
}