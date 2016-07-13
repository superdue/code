abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

val queue = new BasicIntQueue
queue.put(10)
queue.put(20)
println(queue.get())
println(queue.get())

// This declaration means that the trait can only be mixed into a class that also extends IntQueue.
trait Doubling extends IntQueue {
  abstract override def put(x: Int) { super.put(2 * x) }
}

class MyQueue extends BasicIntQueue with Doubling

val queue2 = new MyQueue
queue2.put(10)
println(queue2.get())

val queue3 = new BasicIntQueue with Doubling
queue3.put(10)
println(queue3.get())

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}
trait Filtering extends IntQueue {
  abstract override def put(x: Int) { if (x >= 0) super.put(x) }
}

val queue4 = new BasicIntQueue with Incrementing with Filtering
queue4.put(-1); queue4.put(0); queue4.put(1)
println(queue4.get())
println(queue4.get())