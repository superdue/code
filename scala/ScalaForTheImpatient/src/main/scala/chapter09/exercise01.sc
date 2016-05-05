package chapter09

object exercise01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import scala.io.Source
  import java.io.PrintWriter
  val out = new PrintWriter("01.txt")             //> out  : java.io.PrintWriter = java.io.PrintWriter@159b5217
  val source = Source.fromFile("01.out").getLines.toArray[String].reverse.foreach(out.println(_))
                                                  //> java.io.FileNotFoundException: 01.out (No such file or directory)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(FileInputStream.java:120)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:90)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:75)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:53)
                                                  //| 	at chapter09.exercise01$$anonfun$main$1.apply$mcV$sp(chapter09.exercise0
                                                  //| 1.scala:9)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at chapter09.exercise01$.main(chapter09.exercise01.scala:3)
                                                  //| 	at chapter09.exercise01.main(chapter09.exercise01.scala)
  out.close()
}