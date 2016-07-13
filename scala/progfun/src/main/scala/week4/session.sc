package week41

object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val x: List[String] = Nil                       //> x  : week41.List[String] = week41.Nil$@2d342ba4
}

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}