package chapter11

object exercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class ASCIIArt(val art: String) {

    def +(other: ASCIIArt) = new ASCIIArt(
      art.split("\n").zip(other.art.split("\n")).map(x => x._1 + x._2).mkString("\n"))

    def ^(other: ASCIIArt) = new ASCIIArt(
      art + "\n" + other.art)

    override def toString = art
  }

  val x = new ASCIIArt(
    """ /\_/\
( ' ' )
(  -  )
 | | |
(__|__)""")                                       //> x  : chapter11.exercise06.ASCIIArt =  /\_/\
                                                  //| ( ' ' )
                                                  //| (  -  )
                                                  //|  | | |
                                                  //| (__|__)

  val y = new ASCIIArt(
    """   -----
 / Hello \
<  Scala |
 \ Coder /
   -----""")                                      //> y  : chapter11.exercise06.ASCIIArt =    -----
                                                  //|  / Hello \
                                                  //| <  Scala |
                                                  //|  \ Coder /
                                                  //|    -----

  x + y                                           //> res0: chapter11.exercise06.ASCIIArt =  /\_/\   -----
                                                  //| ( ' ' ) / Hello \
                                                  //| (  -  )<  Scala |
                                                  //|  | | | \ Coder /
                                                  //| (__|__)   -----
  x ^ y                                           //> res1: chapter11.exercise06.ASCIIArt =  /\_/\
                                                  //| ( ' ' )
                                                  //| (  -  )
                                                  //|  | | |
                                                  //| (__|__)
                                                  //|    -----
                                                  //|  / Hello \
                                                  //| <  Scala |
                                                  //|  \ Coder /
                                                  //|    -----
}