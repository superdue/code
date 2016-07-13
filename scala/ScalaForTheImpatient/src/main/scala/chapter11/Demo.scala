package chapter11

object Demo {

  def main(args: Array[String]) {

    def filterZero(lst: List[Int]): List[Int] = lst match { 
  case Nil => Nil
  case h :: t => if (h != 0) h :: filterZero(t) else filterZero(t) 
}

val x = List(5, 7, 0, 18, 22, 0, 12, 1, 0, 5, 0)

println(x)

val y = filterZero(x)

println(y)
  }
}