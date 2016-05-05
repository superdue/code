package chapter09

object exercise03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  io.Source.fromFile("01.txt").mkString.split("""[\s"\.,\)\()/]+""").filter(_.length > 12).distinct.foreach(println(_))
}