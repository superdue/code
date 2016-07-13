object Queue {
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}

// change
class Queue[+T](
  private[this] var leading: List[T],
  private[this] var trailing: List[T]
) {
  private def mirror() = 
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: Queue[T] = {
    mirror()
    new Queue(leading.tail, trailing)
  }

  def enqueue[U >: T](x: U) =
    new Queue[U](leading, x :: trailing)

  override def toString =
    leading ::: trailing.reverse mkString ","
}

// test code

val q = Queue(1, 2, 3)
val q1 = q enqueue 4
println(q)
println(q1)