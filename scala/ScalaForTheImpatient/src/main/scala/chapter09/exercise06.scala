package chapter09

object exercise06 extends App {

  val pattern = """"([^"\\]*([\\]+"[^"\\]*)*)"""".r

  for (str <- io.Source.stdin.getLines) {
    pattern findFirstIn str match {
      case Some(s: String) => println(s)
      case None =>
    }
  }

}