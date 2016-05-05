class SlowAppendQueue[T](elems: List[T]) {
  def head = elems.head
  def tail = new SlowAppendQueue(elems.tail)
  def enqueue(x: T) = new SlowAppendQueue(elems ::: List(x))
}

class SlowHeadQueue[T](smele: List[T]) {
  // smele is elems reversed
  def head = smele.last
  def tail = new SlowHeadQueue(smele.init)
  def enqueue(x: T) = new SlowHeadQueue(x :: smele)
}

object Queue {
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}

class Queue[T](
  private val leading: List[T],
  private val trailing: List[T]
) {
  private def mirror = 
    if (leading.isEmpty)
      new Queue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head

  def tail = {
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
  }

  def enqueue(x: T) =
    new Queue(leading, x :: trailing)

  override def toString =
    leading ::: trailing.reverse mkString ","
}

// test code

val q = Queue(1, 2, 3)
val q1 = q enqueue 4
println(q)
println(q1)