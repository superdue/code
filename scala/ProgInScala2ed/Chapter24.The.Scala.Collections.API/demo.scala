// Why have both Traversable and Iterable?

sealed abstract class Tree extends Traversable[Int] {
  def foreach[U](f: Int => U) = this match {
    case Node(elem) => f(elem)
    case Branch(l, r) => l foreach f; r foreach f
  }
}
// 2N

sealed abstract class Tree extends Iterable[Int] {
  def iterator: Iterator[Int] = this match {
    case Node(elem) => Iterator.single(elem)
    case Branch(l, r) => l.iterator ++ r.iterator
  }
}
// N log(N)
case class Branch(left: Tree, right: Tree) extends Tree
case class Node(elem: Int) extends Tree

// If the tree has a million elements that means about two million steps for foreach and about twenty million steps for iterator.
// So the foreach solution has a clear advantage.



