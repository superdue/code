package chapter08

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import java.awt.Rectangle

  class Square(x: Int, y: Int, width: Int) extends Rectangle(x, y, width, width) {
    def this() = this(0, 0, 0)
    def this(width: Int) = this(0, 0, width)
  }

  val s1 = new Square(1, 1, 3)                    //> s1  : chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=1,y=1,width=3,height=3]
  s1                                              //> res0: chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=1,y=1,width=3,height=3]

  val s2 = new Square()                           //> s2  : chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=0,y=0,width=0,height=0]
  s2                                              //> res1: chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=0,y=0,width=0,height=0]

  val s3 = new Square(5)                          //> s3  : chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=0,y=0,width=5,height=5]
  s3                                              //> res2: chapter08.exercise07.Square = chapter08.exercise07$$anonfun$main$1$Squ
                                                  //| are$1[x=0,y=0,width=5,height=5]|
}