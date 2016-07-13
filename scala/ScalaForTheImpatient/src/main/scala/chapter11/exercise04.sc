package chapter11

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // 不需要提供*和/操作。对于金额来说没有乘除操作

  /**
   * Because of the Ordered[Money] trait, we get the comparison operators
   * for free when compareTo is overridden.
   * Don't recommend
   */
  class Money(val d: BigInt, val c: BigInt) extends Ordered[Money] {
    private var ds = c /% 100;
    val cents = ds._2
    val dollars = d + ds._1

    def +(that: Money): Money = {
      new Money(0, this.totalCents + that.totalCents)
    }

    def -(that: Money): Money = {
      new Money(0, this.totalCents - that.totalCents)
    }

    def totalCents: BigInt = {
      (dollars * 100) + cents
    }

    def ==(that: Money): Boolean = {
      compareTo(that) == 0
    }

    def *(that: Int) = {
      new Money(0, this.totalCents * that)
    }
    def /(that: Int) = {
      new Money(0, this.totalCents / that)
    }

    override def compare(that: Money): Int = {
      totalCents.compare(that.totalCents)
    }
  }

  object Money {
    def apply(d: Int, c: Int) = new Money(d, c)
  }

  Money(1, 75) + Money(0, 50) == Money(2, 25)     //> res0: Boolean = true
}