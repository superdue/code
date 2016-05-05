package chapter14

object exercise05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def sumLeaf(lst: List[Any]): Int =
    (for (elem <- lst) yield (
      elem match {
        case x: List[Int] => sumLeaf(x)
        case x: Int => x
        case _ => 0
      }
    )).sum                                        //> sumLeaf: (lst: List[Any])Int
    
  val x = List(List(3, 8), 2, List(5))            //> x  : List[Any] = List(List(3, 8), 2, List(5))
  
  sumLeaf(x)                                      //> res0: Int = 18
  
  
  // 另外一种解法，这种解法没有泛型警告
  
  def leafSumImpl(total: Int, nodes: List[Any]): Int = {
    nodes match {
      case List() => total
      case head :: tail =>
        head match {
          case x: Int => leafSumImpl(x + total, tail)
          case y: List[Any] => leafSumImpl(total, y ::: tail)
          case _ => total
        }
    }
  }                                               //> leafSumImpl: (total: Int, nodes: List[Any])Int
  def leafSum(tree: List[Any]): Int = {
    val result = leafSumImpl(0, tree)
    result
  }                                               //> leafSum: (tree: List[Any])Int
  leafSum(x)                                      //> res1: Int = 18
  
  // TIPS
  List(3, 8) ::: List(2, List(5))                 //> res2: List[Any] = List(3, 8, 2, List(5))
}