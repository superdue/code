package chapter14

object exercise06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
  
  def leafSum(tree: BinaryTree): Int = tree match {
    case Node(left, right) => leafSum(left) + leafSum(right)
    case Leaf(x) => x
  }                                               //> leafSum: (tree: chapter14.exercise06.BinaryTree)Int
  
  val x = Node(Node(Leaf(3), Leaf(8)), Leaf(5))   //> x  : chapter14.exercise06.Node = Node(Node(Leaf(3),Leaf(8)),Leaf(5))
  
  leafSum(x)                                      //> res0: Int = 16
}