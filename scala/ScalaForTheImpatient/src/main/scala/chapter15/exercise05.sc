package chapter15

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def linesOfFilename(filename: String): String = {
    io.Source.fromFile(filename).mkString
  }                                               //> linesOfFilename: (filename: String)String
}