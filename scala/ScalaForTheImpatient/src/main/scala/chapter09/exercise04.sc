package chapter09

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val numbers = io.Source.fromFile("04.txt").mkString.split("""\s+""").map(_.toDouble)
                                                  //> java.io.FileNotFoundException: 04.txt (No such file or directory)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(FileInputStream.java:120)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:90)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:75)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:53)
                                                  //| 	at chapter09.exercise04$$anonfun$main$1.apply$mcV$sp(chapter09.exercise0
                                                  //| 4.scala:6)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at chapter09.exercise04$.main(chapter09.exercise04.scala:3)
                                                  //| 	at chapter09.exercise04.main(chapter09.exercise04.scala)

  println("Numbers: " + numbers.mkString(", "))

  println("Sum: " + numbers.sum)
  println("Avg: " + numbers.sum / numbers.length)
  println("Min: " + numbers.min)
  println("Max: " + numbers.max)
}