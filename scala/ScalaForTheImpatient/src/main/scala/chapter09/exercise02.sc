package chapter09

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import scala.io.Source
  import java.io.PrintWriter

  val column = 8                                  //> column  : Int = 8
  var count: Int = 0                              //> count  : Int = 0

  val source = Source.fromFile("02.txt")          //> java.io.FileNotFoundException: 02.txt (No such file or directory)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(FileInputStream.java:120)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:90)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:75)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:53)
                                                  //| 	at chapter09.exercise02$$anonfun$main$1.apply$mcV$sp(chapter09.exercise0
                                                  //| 2.scala:12)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at chapter09.exercise02$.main(chapter09.exercise02.scala:3)
                                                  //| 	at chapter09.exercise02.main(chapter09.exercise02.scala)
  val out = new PrintWriter("02.out")

  for (c <- source) c match {
    case '\t' => {
      out.print(" " * (column - count % column))
      count = 0
    }
    case '\n' => {
      out.print(c)
      count = 0
    }
    case _ => {
      out.print(c)
      count += 1
    }
  }

  source.close()
  out.close()
}