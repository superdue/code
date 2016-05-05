val empty = scala.collection.immutable.Queue[Int]()

val has1 = empty.enqueue(1)

val has123 = has1.enqueue(List(2, 3))

val (element, has23) = has123.dequeue

