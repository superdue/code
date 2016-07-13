package day2

object session {
  def subset(line1: List[Char], line2: List[Char]): Boolean = line1 match {
    case Nil => true
    case x :: xs => if (line2.contains(x)) subset(xs, line2) else false
  }                                               //> subset: (line1: List[Char], line2: List[Char])Boolean
  def lines() = {
    val Line1 = "abc".toList
    val Line2 = "adef".toList
    val Line3 = "aghi".toList
    val Line4 = "ajk".toList
    val Line5 = "bdgj".toList
    val Line6 = "cehj".toList
    val Line7 = "cfik".toList
    List(Line1, Line2, Line3, Line4, Line5, Line6, Line7)
  }                                               //> lines: ()List[List[Char]]

  def contains(line1: List[Char], line2: List[Char]): Boolean = line1 match {
    case Nil => true
    case x :: xs => if (line2.contains(x)) contains(xs, line2) else false
  }                                               //> contains: (line1: List[Char], line2: List[Char])Boolean
  def belong(line: List[Char], lines: List[List[Char]]): Boolean = lines match {
    case Nil => false
    case x :: xs => if (contains(line, x)) true else belong(line, xs)
  }                                               //> belong: (line: List[Char], lines: List[List[Char]])Boolean

  def connected(p1: Char, p2: Char): Boolean = belong(List(p1, p2), lines())
                                                  //> connected: (p1: Char, p2: Char)Boolean
  def on_a_line(p1: Char, p2: Char, p3: Char) = belong(List(p1, p2, p3), lines())
                                                  //> on_a_line: (p1: Char, p2: Char, p3: Char)Boolean
  def triangle(points: List[Char]): Boolean = points match {
    case p1 :: p2 :: p3 :: Nil => connected(p1, p2) && connected(p1, p3) && connected(p2, p3) && !on_a_line(p1, p2, p3)
    case _ => false
  }                                               //> triangle: (points: List[Char])Boolean
  def comb(points: List[Char], n: Int): List[List[Char]] = points match {
    case Nil => Nil
    case x :: xs if (n == 1) => List(List(x)) ++ comb(xs, 1)
    case x :: xs => comb(xs, n-1).map(x :: _) ++ comb(xs, n)
  }                                               //> comb: (points: List[Char], n: Int)List[List[Char]]

  def points() = "abcdefghijk".toList             //> points: ()List[Char]
  def triple_points() = comb(points(), 3)         //> triple_points: ()List[List[Char]]
  triple_points().filter(triangle(_)).map(_.mkString).mkString(",")
                                                  //> res0: String = abd,abg,abj,ace,acf,ach,aci,acj,ack,adg,adj,aeh,aej,afi,afk,
                                                  //| agj,ahj,aik,bcj,cef,chi,cjk,dej,ghj

}