package chapter14

object exercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(leafs: BinaryTree*) extends BinaryTree
  
  def leafSum(tree: BinaryTree): Int = tree match {
    case Node(leafs @ _*) => leafs.map(leafSum _).sum
    case Leaf(x) => x
  }                                               //> leafSum: (tree: chapter14.exercise07.BinaryTree)Int
  
  val x = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
                                                  //> x  : chapter14.exercise07.Node = Node(WrappedArray(Node(WrappedArray(Leaf(3)
                                                  //| , Leaf(8))), Leaf(2), Node(WrappedArray(Leaf(5)))))
  
  leafSum(x)                                      //> res0: Int = 18
}