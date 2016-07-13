package chapter14

object exercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(op: Char, leafs: BinaryTree*) extends BinaryTree
  
  def eval(tree: BinaryTree): Int = tree match {
    case Node(op, leafs @ _*) => op match {
      case '+' => leafs.map(eval _).sum
      case '-' => -leafs.map(eval _).sum
      case '*' => leafs.map(eval _).product
    }
    case Leaf(x) => x
  }                                               //> eval: (tree: chapter14.exercise08.BinaryTree)Int
  
  val x = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))
                                                  //> x  : chapter14.exercise08.Node = Node(+,WrappedArray(Node(*,WrappedArray(Lea
                                                  //| f(3), Leaf(8))), Leaf(2), Node(-,WrappedArray(Leaf(5)))))
  
  eval(x)                                         //> res0: Int = 21
}