package chapter09

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val tokens = io.Source.fromFile("07.txt").mkString.split("""\s+""").filter(
    "^[0-9]+(\\.[0-9]+)?$".r findFirstIn _ match {
      case Some(_) => false
      case None => true
    })                                            //> java.io.FileNotFoundException: 07.txt (No such file or directory)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(FileInputStream.java:120)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:90)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:75)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:53)
                                                  //| 	at chapter09.exercise07$$anonfun$main$1.apply$mcV$sp(chapter09.exercise0
                                                  //| 7.scala:6)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at chapter09.exercise07$.main(chapter09.exercise07.scala:3)
                                                  //| 	at chapter09.exercise07.main(chapter09.exercise07.scala)

  "Tokents: " + tokens.mkString(", ")
}